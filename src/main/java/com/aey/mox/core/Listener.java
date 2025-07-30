package com.aey.mox.core;

import java.util.Optional;

import com.aey.mox.listeners.EventListener;

public class Listener {

    private EventListener eventListener;
    private Optional<?> some;

    public Listener(EventListener eventListener) {
        this.eventListener = eventListener;
        this.some = Optional.empty();
    }

    public EventListener getEventListener() {
        return this.eventListener;
    }

    public final <T> void setSome(T obj) {
        this.some = Optional.ofNullable(obj);
    }

    public final Optional<?> some() {
        return this.some;
    }

    @Override
    public String toString() {
        return "Listener [eventListener=" + eventListener + ", some=" + some + "]";
    }

}
