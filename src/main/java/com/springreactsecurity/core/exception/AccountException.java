package com.springreactsecurity.core.exception;

public class AccountException extends BaseException {

    public AccountException(ErrorType errorType) {
        super(errorType);
    }

}
