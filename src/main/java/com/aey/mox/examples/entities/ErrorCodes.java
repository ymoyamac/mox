package com.aey.mox.examples.entities;

public enum ErrorCodes {
    NOT_FOUND("404", "Not found", "User not found")
    ;
    
    private final String code;
    private final String msg;
    private final String errContext;

    ErrorCodes(String code, String msg, String errContext) {
        this.code = code;
        this.msg = msg;
        this.errContext = errContext;

    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getErrContext() {
        return errContext;
    }

    
}
