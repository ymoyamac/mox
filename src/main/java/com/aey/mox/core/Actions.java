package com.aey.mox.core;

public enum Actions {
    PUBLISH("publish")
    ;

    private final String action;

    Actions(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }
}
