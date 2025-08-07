package com.aey.examples._01;


import com.aey.mox.listeners.EventListener;

public class CustomContext implements EventListener {

    @Override
    public <U> void update(String eventType, U event) {
        System.out.println("CustomeContext: " + event.toString());
    }

    @Override
    public String toString() {
        return "CustomContext []";
    }

}