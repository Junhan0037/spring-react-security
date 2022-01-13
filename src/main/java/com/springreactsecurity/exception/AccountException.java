package com.springreactsecurity.exception;

public class AccountException extends BaseException {

    public AccountException(ErrorType errorType) {
        super(errorType);
    }

}
