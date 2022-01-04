package com.springreactsecurity.exception;

public class BaseException extends RuntimeException {

    protected MsgType msgType;

    protected String[] msgArgs;

    public BaseException(MsgType msgType, String[] args) {
        this.msgType = msgType;
        this.msgArgs = args;
    }

    public BaseException(MsgType msgType) {
        this.msgType = msgType;
        this.msgArgs = new String[] {};
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public String[] getMsgArgs() {
        return msgArgs;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

}
