package com.springreactsecurity.domain.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class MemberDto {

    /* Request */
    @Data
    public static class signUpForm {
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
    public static class loginForm {
        @NotEmpty(message = "Required Id")
        private String userId;

        @NotEmpty(message = "Required Password")
        private String userPassword;
    }

    @Data
    public static class findIdForm {
        @NotEmpty(message = "Required Name")
        private String name;

        @NotEmpty(message = "Required Email")
        private String email;
    }

    @Data
    public static class findPasswordForm {
        @NotEmpty(message = "Required Id")
        private String userId;

        @NotEmpty(message = "Required Name")
        private String name;

        @NotEmpty(message = "Required Email")
        private String email;
    }

    @Data
    public static class editMyInfoForm {
        private String name;
        private String email;

        @NotEmpty(message = "Required Password")
        private String userPassword;
    }

    /* Response */
    @Data
    public static class memberForm {
        private String userId;
        private String name;
        private String email;
    }

}
