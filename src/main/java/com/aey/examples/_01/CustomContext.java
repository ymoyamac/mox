package com.aey.examples._01;

import java.util.Optional;

import com.aey.mox.listeners.EventListener;

public class CustomContext implements EventListener {

    private Optional<User> ok;

    @Override
    public <U> void update(String eventType, U event) {
        System.out.println("CustomeContext: " + event.toString());
        this.ok = Optional.ofNullable((User) event);
    }

    public Optional<User> ok() {
        return this.ok;
    }
    
}