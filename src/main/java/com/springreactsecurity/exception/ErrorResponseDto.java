package com.springreactsecurity.exception;

import lombok.Data;

@Data
public class ErrorResponseDto {

    private String message;

    private String errorMessage;

    private String errorDetailMessage;

}
