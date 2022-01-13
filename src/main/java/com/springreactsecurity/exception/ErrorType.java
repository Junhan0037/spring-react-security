package com.springreactsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    // User
    USER_NOT_EXISTS("USER1", "사용자가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_LOGGED_IN("USER2", "로그인이 필요한 작업입니다.", HttpStatus.UNAUTHORIZED),

    // Security
    UNAUTHENTICATED("SECURITY1", "로그인이 필요한 기능입니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("SECURITY2", "권한이 없습니다.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus status;

}
