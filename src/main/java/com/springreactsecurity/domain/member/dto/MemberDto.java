package com.springreactsecurity.domain.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class MemberDto {

    @Data
    public static class signUpForm {
        @NotEmpty(message = "Required Email")
        @Email
        private String email;

        @NotEmpty(message = "Required Password")
        private String password;

        @NotEmpty(message = "Required PasswordConfirm")
        private String passwordConfirm;

        @NotEmpty(message = "Required Name")
        private String name;
    }

    @Data
    public static class loginForm {
        @NotEmpty(message = "Required Email")
        @Email
        private String email;

        @NotEmpty(message = "Required Password")
        private String password;
    }

}
