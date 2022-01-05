package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberDto;
import com.springreactsecurity.exception.AccountException;
import com.springreactsecurity.exception.MsgType;
import com.springreactsecurity.security.UserMember;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean signUp(MemberDto.signUpForm signUpForm) {
        // Form 검증
        validateSignUpForm(signUpForm);

        // 회원가입 및 저장
        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Member member = modelMapper.map(signUpForm, Member.class);
        member.setRole(Role.USER);
        Member savedMember = memberRepository.save(member);

        // 로그인
        login(savedMember);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + "를 찾을 수 없습니다."));
        return new UserMember(member);
    }

    private void validateSignUpForm(MemberDto.signUpForm signUpForm) {
        if (!signUpForm.getPassword().equals(signUpForm.getPasswordConfirm())) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"비밀번호가 틀립니다."});
        }

        Optional<Member> optionalMember = memberRepository.findByEmail(signUpForm.getEmail());

        if (optionalMember.isPresent()) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"아이디가 이미 존재합니다."});
        }
    }

    private void login(Member member) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                new UserMember(member),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority(member.getRole().getKey())));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
