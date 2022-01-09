package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberRequestDto;
import com.springreactsecurity.domain.member.dto.MemberResponseDto;
import com.springreactsecurity.exception.AccountException;
import com.springreactsecurity.exception.MsgType;
import com.springreactsecurity.security.UserMember;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDto.signUpForm signUp(MemberRequestDto.signUpForm signUpForm) {
        // Form 검증
        validateSignUpForm(signUpForm);

        // 회원가입 및 저장
        signUpForm.setUserPassword(passwordEncoder.encode(signUpForm.getUserPassword()));
        Member member = modelMapper.map(signUpForm, Member.class);
        member.setRole(Role.USER);
        Member savedMember = memberRepository.save(member);

        // 로그인
        login(savedMember);

        return modelMapper.map(savedMember, MemberResponseDto.signUpForm.class);
    }

    private void validateSignUpForm(MemberRequestDto.signUpForm signUpForm) {
        if (!signUpForm.getUserPassword().equals(signUpForm.getUserPasswordConfirm())) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"비밀번호가 틀립니다."});
        }

        Optional<Member> optionalMember = memberRepository.findByUserId(signUpForm.getUserId());

        if (optionalMember.isPresent()) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"아이디가 이미 존재합니다."});
        }
    }

    private void login(Member member) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                new UserMember(member),
                member.getUserPassword(),
                List.of(new SimpleGrantedAuthority(member.getRole().getKey())));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
