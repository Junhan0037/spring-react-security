package com.springreactsecurity.exception;

public enum MsgType {

    UnknownError("M999"),
    ServerError("M000"),
    NoExistUsername("M001"),
    ExistUserId("M002"),
    RequiredLogin("M003"),
    NoAuth("M004"),
    UnknownParameter("M005"),
    MailError("M006");

    private final String msgCode;

    MsgType(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgCode() {
        return msgCode;
    }

}
