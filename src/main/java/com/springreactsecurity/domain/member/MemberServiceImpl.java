package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberDto;
import com.springreactsecurity.exception.AccountException;
import com.springreactsecurity.exception.MsgType;
import com.springreactsecurity.mail.EmailMessage;
import com.springreactsecurity.mail.EmailService;
import com.springreactsecurity.security.UserMember;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
    private final TemplateEngine templateEngine;
    private final EmailService emailService;

    @Override
    public MemberDto.memberForm signUp(MemberDto.signUpForm signUpForm) {
        // Form 검증
        validateSignUpForm(signUpForm);

        // 회원가입 및 저장
        signUpForm.setUserPassword(passwordEncoder.encode(signUpForm.getUserPassword()));
        Member member = modelMapper.map(signUpForm, Member.class);
        member.setRole(Role.USER);
        Member savedMember = memberRepository.save(member);

        // 가입 확인 메일 전송
        sendSignUpConfirmEmail(savedMember);

        // 로그인
        login(savedMember);

        return modelMapper.map(savedMember, MemberDto.memberForm.class);
    }

    @Override
    public String findId(MemberDto.findIdForm findIdForm) {
        // 회원 찾기
        Optional<Member> optionalMember = memberRepository.findByNameAndEmail(findIdForm.getName(), findIdForm.getEmail());

        // 에러 핸들링
        if (optionalMember.isEmpty()) {
            throw new AccountException(MsgType.NoExistUsername, new String[]{"해당 회원이 존재하지 않습니다."});
        }

        return optionalMember.get().getUserId();
    }

    @Override
    public String findPassword(MemberDto.findPasswordForm findPasswordForm) {
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

    @Override
    public MemberDto.memberForm editMyInfo(MemberDto.editMyInfoForm editMyInfoForm, String userId) {
        String name = editMyInfoForm.getName();
        String email = editMyInfoForm.getEmail();
        String userPassword = editMyInfoForm.getUserPassword();

        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new AccountException(MsgType.NoExistUsername, new String[]{userId + " 를 찾을 수 없습니다."}));

        validatePassword(userPassword, member.getUserPassword());

        if (!name.isEmpty()) {
            member.setName(name);
        }

        if (!email.isEmpty()) {
            member.setEmail(email);
        }

        Member savedMember = memberRepository.save(member);

        return modelMapper.map(savedMember, MemberDto.memberForm.class);
    }

    @Override
    public MemberDto.memberForm editMyPassword(MemberDto.editMyPasswordForm editMyPasswordForm, String userId) {
        String userPastPassword = editMyPasswordForm.getUserPastPassword();
        String userNewPassword = editMyPasswordForm.getUserNewPassword();
        String userNewPasswordConfirm = editMyPasswordForm.getUserNewPasswordConfirm();

        if (!userNewPassword.equals(userNewPasswordConfirm)) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"비밀번호와 비밀번호 확인이 틀립니다."});
        }

        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new AccountException(MsgType.NoExistUsername, new String[]{userId + " 를 찾을 수 없습니다."}));

        if (!passwordEncoder.matches(userPastPassword, member.getUserPassword())) {
            throw new AccountException(MsgType.NoAuth, new String[]{"기존 비밀번호가 틀립니다."});
        }

        member.setUserPassword(passwordEncoder.encode(userNewPassword));
        Member savedMember = memberRepository.save(member);

        return modelMapper.map(savedMember, MemberDto.memberForm.class);
    }

    /**
     * 회원가입 시 Dto 검증
     * @param signUpForm 회원가입 폼
     */
    private void validateSignUpForm(MemberDto.signUpForm signUpForm) {
        if (!signUpForm.getUserPassword().equals(signUpForm.getUserPasswordConfirm())) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"비밀번호와 비밀번호 확인이 틀립니다."});
        }

        Optional<Member> optionalMember = memberRepository.findByUserId(signUpForm.getUserId());

        if (optionalMember.isPresent()) {
            throw new AccountException(MsgType.UnknownParameter, new String[]{"아이디가 이미 존재합니다."});
        }
    }

    /**
     * Email 전송
     * @param member 가입한 회원
     */
    private void sendSignUpConfirmEmail(Member member) {
        // Thymeleaf 변수 설정
        Context context = new Context();
        context.setVariable("name", member.getName());
        context.setVariable("message", "회원 가입을 환영합니다.");
        context.setVariable("image_1", "https://previews.123rf.com/images/maxborovkov/maxborovkov1703/maxborovkov170300174/74747035-%EB%8B%A4%EC%B1%84%EB%A1%9C%EC%9A%B4-%EC%83%89%EC%A2%85%EC%9D%B4%EC%99%80-%EC%A2%85%EC%9D%B4-%EB%B0%B0%EB%84%88%EB%A5%BC-%ED%99%98%EC%98%81%ED%95%A9%EB%8B%88%EB%8B%A4-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%A0%88%EC%9D%B4-%EC%85%98-.jpg");

        // simple-link.html 을 메일 내용으로 설정
        String message = templateEngine.process("mail/simple-link", context);

        // Entity 생성
        EmailMessage emailMessage = EmailMessage.builder()
                                                .to(member.getEmail())
                                                .subject("회원 가입을 환영합니다!")
                                                .message(message)
                                                .build();

        // 메일 발송
        emailService.sendEmail(emailMessage);
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

    /**
     * 입력한 비밀번호 확인
     * @param inputPassword 입력 패스워드
     * @param memberPassword 회원 패스워드
     */
    private void validatePassword(String inputPassword, String memberPassword) {
        if (!passwordEncoder.matches(inputPassword, memberPassword)) {
            throw new AccountException(MsgType.NoAuth, new String[]{"비밀번호를 확인해주세요."});
        }
    }

}
