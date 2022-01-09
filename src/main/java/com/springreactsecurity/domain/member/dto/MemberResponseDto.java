package com.springreactsecurity.domain.member.dto;

import lombok.Data;

public class MemberResponseDto {

    @Data
    public static class signUpForm {
        private String userId;
        private String name;
        private String email;
    }

}
