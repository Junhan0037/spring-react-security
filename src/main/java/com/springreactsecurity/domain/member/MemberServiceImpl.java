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
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDto.memberForm signUp(MemberRequestDto.signUpForm signUpForm) {
        // Form 검증
        validateSignUpForm(signUpForm);

        // 회원가입 및 저장
        signUpForm.setUserPassword(passwordEncoder.encode(signUpForm.getUserPassword()));
        Member member = modelMapper.map(signUpForm, Member.class);
        member.setRole(Role.USER);
        Member savedMember = memberRepository.save(member);

        // 로그인
        login(savedMember);

        return modelMapper.map(savedMember, MemberResponseDto.memberForm.class);
    }

    @Override
    public String findId(MemberRequestDto.findIdForm findIdForm) {
        // 회원 찾기
        Optional<Member> optionalMember = memberRepository.findByNameAndEmail(findIdForm.getName(), findIdForm.getEmail());

        // 에러 핸들링
        if (optionalMember.isEmpty()) {
            throw new AccountException(MsgType.NoExistUsername, new String[]{"해당 회원이 존재하지 않습니다."});
        }

        return optionalMember.get().getUserId();
    }

    @Override
    public String findPassword(MemberRequestDto.findPasswordForm findPasswordForm) {
        // 회원 찾기
        Optional<Member> optionalMember = memberRepository.findByUserIdAndNameAndEmail(findPasswordForm.getUserId(), findPasswordForm.getName(), findPasswordForm.getEmail());

        // 에러 핸들링
        if (optionalMember.isEmpty()) {
            throw new AccountException(MsgType.NoExistUsername, new String[]{"해당 회원이 존재하지 않습니다."});
        }

        // 비밀번호 초기화
        Member member = optionalMember.get();
        String newPassword = UUID.randomUUID().toString();
        member.setUserPassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);

        return newPassword;
    }

    /**
     * 회원가입 시 Dto 검증
     * @param signUpForm 회원가입 폼
     */
    private void validateSignUpForm(MemberRequestDto.signUpForm signUpForm) {
        if (!signUpForm.getUserPassword().equals(signUpForm.getUserPasswordConfirm())) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"비밀번호가 틀립니다."});
        }

        Optional<Member> optionalMember = memberRepository.findByUserId(signUpForm.getUserId());

        if (optionalMember.isPresent()) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"아이디가 이미 존재합니다."});
        }
    }

    /**
     * 회원가입 후 자동 로그인
     * @param member 회원 Entity
     */
    private void login(Member member) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                new UserMember(member),
                member.getUserPassword(),
                List.of(new SimpleGrantedAuthority(member.getRole().getKey())));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
