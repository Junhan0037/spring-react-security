package com.springreactsecurity.exception;

public class MailException extends BaseException{
    public MailException(MsgType msgType, String[] args) {
        super(msgType, args);
    }

    public MailException(MsgType msgType) {
        super(msgType);
    }

    public MsgType getMsgType() {
        return super.msgType;
    }

    public String[] getMsgArgs() {
        return super.msgArgs;
    }
}
