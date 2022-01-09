package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    ResponseEntity<?> signUp(@ModelAttribute @Valid MemberRequestDto.signUpForm signUpForm) {
        return ResponseEntity.ok(memberService.signUp(signUpForm));
    }

    @GetMapping("/find-id")
    ResponseEntity<?> findId(@ModelAttribute @Valid MemberRequestDto.findIdForm findIdForm) {
        return ResponseEntity.ok(memberService.findId(findIdForm));
    }

    @GetMapping("/find-password")
    ResponseEntity<?> findPassword(@ModelAttribute @Valid MemberRequestDto.findPasswordForm findPasswordForm) {
        return ResponseEntity.ok(memberService.findPassword(findPasswordForm));
    }

}
