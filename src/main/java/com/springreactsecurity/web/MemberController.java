package com.springreactsecurity.web;

import com.springreactsecurity.domain.member.Member;
import com.springreactsecurity.domain.member.dto.MemberDto;
import com.springreactsecurity.domain.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 처리한다.")
    @PostMapping("/auth/sign-up")
    ResponseEntity<?> signUp(@ModelAttribute @Valid MemberDto.SignUpForm signUpForm) {
        return ResponseEntity.ok(memberService.signUp(signUpForm));
    }

    @ApiOperation(value = "이메일 인증", notes = "이메일 인증 링크 동작을 처리한다.")
    @GetMapping("/auth/check-email-token")
    ResponseEntity<?> checkEmailToken(String token, String email) {
        return ResponseEntity.ok(memberService.completeSignUp(token, email));
    }

    @ApiOperation(value = "아이디 찾기", notes = "아이디 찾기를 처리한다.")
    @GetMapping("/auth/find-id")
    ResponseEntity<?> findId(@ModelAttribute @Valid MemberDto.FindIdForm findIdForm) {
        return ResponseEntity.ok(memberService.findId(findIdForm));
    }

    @ApiOperation(value = "패스워드 찾기", notes = "패스워드 찾기를 처리한다.")
    @GetMapping("/auth/find-password")
    ResponseEntity<?> findPassword(@ModelAttribute @Valid MemberDto.FindPasswordForm findPasswordForm) {
        return ResponseEntity.ok(memberService.findPassword(findPasswordForm));
    }

    @ApiOperation(value = "접속한 사용자 정보", notes = "접속한 사용자 정보를 불러온다.")
    @GetMapping("/user-info")
    ResponseEntity<?> userInfo() {
        Member member = CurrentUserInfoUtil.getCurrentUserInfo();
        return ResponseEntity.ok(memberService.userInfo(member));
    }

    @ApiOperation(value = "사용자 정보 수정", notes = "자신의 정보를 수정한다.")
    @PostMapping("/edit/my-info")
    ResponseEntity<?> editMyInfo(@ModelAttribute @Valid MemberDto.EditMyInfoForm editMyInfoForm, @CurrentUser Member member) {
        return ResponseEntity.ok(memberService.editMyInfo(editMyInfoForm, member.getUserId()));
    }

    @ApiOperation(value = "사용자 비밀번호 수정", notes = "자신의 비밀번호를 수정한다.")
    @PostMapping("/edit/my-password")
    ResponseEntity<?> editMyPassword(@ModelAttribute @Valid MemberDto.EditMyPasswordForm editMyPasswordForm, @CurrentUser Member member) {
        return ResponseEntity.ok(memberService.editMyPassword(editMyPasswordForm, member.getUserId()));
    }

}
