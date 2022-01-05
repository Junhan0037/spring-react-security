package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberDto;
import com.springreactsecurity.security.CurrentUser;
import com.springreactsecurity.security.CurrentUserInfoUtil;
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
    ResponseEntity<?> signUp(@RequestBody @Valid MemberDto.signUpForm signUpForm) {
        return ResponseEntity.ok(memberService.signUp(signUpForm));
    }

    @GetMapping("/current-user/method")
    ResponseEntity<Member> currentUser() {
        Member member = CurrentUserInfoUtil.getCurrentUserInfo();
        member.setPassword(null);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/current-user/annotation")
    ResponseEntity<Member> currentUserByAnnotation(@CurrentUser Member member) {
        return ResponseEntity.ok(member);
    }

}
