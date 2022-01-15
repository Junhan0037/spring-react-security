package com.springreactsecurity.web;

import com.springreactsecurity.domain.member.Member;
import com.springreactsecurity.domain.member.dto.MemberDto;
import com.springreactsecurity.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/sign-up")
    ResponseEntity<?> signUp(@ModelAttribute @Valid MemberDto.SignUpForm signUpForm) {
        return ResponseEntity.ok(memberService.signUp(signUpForm));
    }

    @GetMapping("/auth/check-email-token")
    ResponseEntity<?> checkEmailToken(String token, String email) {
        return ResponseEntity.ok(memberService.completeSignUp(token, email));
    }

    @GetMapping("/auth/find-id")
    ResponseEntity<?> findId(@ModelAttribute @Valid MemberDto.FindIdForm findIdForm) {
        return ResponseEntity.ok(memberService.findId(findIdForm));
    }

    @GetMapping("/auth/find-password")
    ResponseEntity<?> findPassword(@ModelAttribute @Valid MemberDto.FindPasswordForm findPasswordForm) {
        return ResponseEntity.ok(memberService.findPassword(findPasswordForm));
    }

    @GetMapping("/my-info")
    ResponseEntity<?> myInfo(@CurrentUser Member member) {
        return ResponseEntity.ok(member);
    }

    @PostMapping("/edit/my-info")
    ResponseEntity<?> editMyInfo(@ModelAttribute @Valid MemberDto.EditMyInfoForm editMyInfoForm, @CurrentUser Member member) {
        return ResponseEntity.ok(memberService.editMyInfo(editMyInfoForm, member.getUserId()));
    }

    @PostMapping("/edit/my-password")
    ResponseEntity<?> editMyPassword(@ModelAttribute @Valid MemberDto.EditMyPasswordForm editMyPasswordForm, @CurrentUser Member member) {
        return ResponseEntity.ok(memberService.editMyPassword(editMyPasswordForm, member.getUserId()));
    }

}
