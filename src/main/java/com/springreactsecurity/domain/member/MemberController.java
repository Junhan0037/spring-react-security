package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberDto;
import com.springreactsecurity.security.CurrentUser;
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
    ResponseEntity<?> signUp(@ModelAttribute @Valid MemberDto.signUpForm signUpForm) {
        return ResponseEntity.ok(memberService.signUp(signUpForm));
    }

    @GetMapping("/auth/find-id")
    ResponseEntity<?> findId(@ModelAttribute @Valid MemberDto.findIdForm findIdForm) {
        return ResponseEntity.ok(memberService.findId(findIdForm));
    }

    @GetMapping("/auth/find-password")
    ResponseEntity<?> findPassword(@ModelAttribute @Valid MemberDto.findPasswordForm findPasswordForm) {
        return ResponseEntity.ok(memberService.findPassword(findPasswordForm));
    }

    @GetMapping("/my-info")
    ResponseEntity<?> myInfo(@CurrentUser Member member) {
        return ResponseEntity.ok(member);
    }

    @PostMapping("/edit/my-info")
    ResponseEntity<?> editMyInfo(@ModelAttribute @Valid MemberDto.editMyInfoForm editMyInfoForm, @CurrentUser Member member) {
        return ResponseEntity.ok(memberService.editMyInfo(editMyInfoForm, member.getUserId()));
    }

}
