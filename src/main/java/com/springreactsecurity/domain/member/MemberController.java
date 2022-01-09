package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    ResponseEntity<?> signUp(@ModelAttribute @Valid MemberRequestDto.signUpForm signUpForm) {
        return ResponseEntity.ok(memberService.signUp(signUpForm));
    }

}
