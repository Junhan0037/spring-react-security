package com.springreactsecurity.domain.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

public class AdminDto {

    /* Request */
    @Data
    public static class UserInfoForm {
        @NotEmpty(message = "Required Id")
        private String userId;
    }

    /* Response */

}
