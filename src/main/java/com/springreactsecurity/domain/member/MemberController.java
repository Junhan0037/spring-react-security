package com.springreactsecurity.domain.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController {

    @GetMapping("/sign-up")
    public ResponseEntity<?> signUp() {
        return ResponseEntity.ok("Ok");
    }

}
