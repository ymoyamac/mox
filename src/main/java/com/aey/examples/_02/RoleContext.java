package com.aey.examples._02;

import com.aey.mox.listeners.EventListener;

public class RoleContext implements EventListener {

    @Override
    public <U> void update(String eventType, U event) {
        System.out.println("RoleContext: " + event.toString());
    }

    @Override
    public String toString() {
        return "RoleContext []";
    }
}
