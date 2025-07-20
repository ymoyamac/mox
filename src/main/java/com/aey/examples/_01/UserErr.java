package com.aey.examples._01;

public enum UserErr {

    NOT_FOUND(404, "Not found"),
    SERVER_ERR(500, "International server error")
    ;


    private final Integer code;
    private final String msg;

    UserErr(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    
        
}
