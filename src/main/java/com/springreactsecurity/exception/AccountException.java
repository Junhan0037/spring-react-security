package com.springreactsecurity.exception;

public class AccountException extends BaseException {
    public AccountException(MsgType msgType, String[] args) {
        super(msgType, args);
    }

    public AccountException(MsgType msgType) {
        super(msgType);
    }

    public MsgType getMsgType() {
        return super.msgType;
    }

    public String[] getMsgArgs() {
        return super.msgArgs;
    }
}
