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

    @Data
    public static class EditUserForm {
        @NotEmpty(message = "Required Id")
        private String userId;

        @NotEmpty(message = "Required Password")
        private String userPassword;

        @NotEmpty(message = "Required Name")
        private String name;

        @NotEmpty(message = "Required Email")
        private String email;
    }

    @Data
    public static class EditUserPasswordForm {
        @NotEmpty(message = "Required Id")
        private String userId;

        @NotEmpty(message = "Required Password")
        private String userPassword;

        @NotEmpty(message = "Required NewUserPassword")
        private String newUserPassword;

        @NotEmpty(message = "Required NewUserPasswordConfirm")
        private String newUserPasswordConfirm;
    }

    /* Response */

}
