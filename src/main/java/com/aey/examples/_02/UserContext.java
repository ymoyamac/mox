package com.aey.examples._02;


import com.aey.mox.listeners.EventListener;

public class UserContext implements EventListener {

    @Override
    public <U> void update(String eventType, U event) {
        System.out.println("UserContext: " + event.toString());
    }

    @Override
    public String toString() {
        return "UserContext []";
    }

}