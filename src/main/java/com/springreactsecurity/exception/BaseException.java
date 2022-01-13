package com.springreactsecurity.exception;

import lombok.Getter;

public class BaseException extends RuntimeException {

    @Getter
    private ErrorType errorType;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

}
