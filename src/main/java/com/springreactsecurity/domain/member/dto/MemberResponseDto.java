package com.springreactsecurity.domain.member.dto;

import lombok.Data;

public class MemberResponseDto {

    @Data
    public static class memberForm {
        private String userId;
        private String name;
        private String email;
    }

}
