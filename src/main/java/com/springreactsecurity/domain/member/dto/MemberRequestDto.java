package com.springreactsecurity.domain.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class MemberRequestDto {

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

}
