package com.aey.mox.examples.entities;

import com.aey.mox.core.Err;

public class MocaErr extends Err {
    private String errContext;

    public MocaErr() {
        
    }

    public String getErrContext() {
        return errContext;
    }

    public void setErrContext(String errContext) {
        this.errContext = errContext;
    }

    @Override
    public String toString() {
        return " MocaErr ["+ "\n" +
                            "       code=" + super.getCode() + ",\n" +
                            "       code=" + super.getMsg() + ",\n" +
                            "       errContext=" + errContext;
    }

    
}
