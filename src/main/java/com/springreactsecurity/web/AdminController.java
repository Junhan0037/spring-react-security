package com.springreactsecurity.web;

import com.springreactsecurity.domain.admin.dto.AdminDto;
import com.springreactsecurity.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/user-info")
    ResponseEntity<?> userInfo(@ModelAttribute @Valid AdminDto.UserInfoForm userInfoForm) {
        return ResponseEntity.ok(adminService.getUserInfo(userInfoForm));
    }

    @GetMapping("/user-info/all")
    ResponseEntity<?> userInfoAll() {
        return ResponseEntity.ok(adminService.getUserInfoAll());
    }

    @PostMapping("/edit/user-info")
    ResponseEntity<?> editUserInfo(@ModelAttribute @Valid AdminDto.EditUserForm editUserForm) {
        return ResponseEntity.ok(adminService.editUserInfo(editUserForm));
    }

    @PostMapping("/edit/user-password")
    ResponseEntity<?> editUserPassword(@ModelAttribute @Valid AdminDto.EditUserPasswordForm editUserPasswordForm) {
        return ResponseEntity.ok(adminService.editUserPassword(editUserPasswordForm));
    }

}
