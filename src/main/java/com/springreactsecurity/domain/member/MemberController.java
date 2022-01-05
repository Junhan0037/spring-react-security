package com.springreactsecurity.domain.member;

import com.springreactsecurity.security.CurrentUserInfoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController {

    @GetMapping("/sign-up")
    ResponseEntity<?> signUp() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/user")
    ResponseEntity<Member> currentUser() {
        Member member = CurrentUserInfoUtil.getCurrentUserInfo();
        return ResponseEntity.ok(member);
    }

}
