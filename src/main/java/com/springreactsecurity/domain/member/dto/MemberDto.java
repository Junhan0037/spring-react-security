package com.springreactsecurity.domain.member.dto;

import com.springreactsecurity.domain.member.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class MemberDto {

    /* Request */
    @Data
    public static class SignUpForm {
        @NotEmpty(message = "Required Id")
        private String userId;

        @NotEmpty(message = "Required Password")
        private String userPassword;

        @NotEmpty(message = "Required PasswordConfirm")
        private String userPasswordConfirm;

        @NotEmpty(message = "Required Name")
        private String name;

        @NotEmpty(message = "Required Email")
        @Email
        private String email;
    }

    @Data
    public static class LoginForm {
        @NotEmpty(message = "Required Id")
        private String userId;

        @NotEmpty(message = "Required Password")
        private String userPassword;
    }

    @Data
    public static class FindIdForm {
        @NotEmpty(message = "Required Name")
        private String name;

        @NotEmpty(message = "Required Email")
        private String email;
    }

    @Data
    public static class FindPasswordForm {
        @NotEmpty(message = "Required Id")
        private String userId;

        @NotEmpty(message = "Required Name")
        private String name;

        @NotEmpty(message = "Required Email")
        private String email;
    }

    @Data
    public static class EditMyInfoForm {
        private String name;
        private String email;

        @NotEmpty(message = "Required Password")
        private String userPassword;
    }

    @Data
    public static class EditMyPasswordForm {
        @NotEmpty(message = "Required PastPassword")
        private String userPastPassword;

        @NotEmpty(message = "Required NewPassword")
        private String userNewPassword;

        @NotEmpty(message = "Required NewPasswordConfirm")
        private String userNewPasswordConfirm;
    }

    /* Response */
    @Data
    public static class MemberForm {
        private String userId;
        private String name;
        private String email;
        private Role role;
    }

}
