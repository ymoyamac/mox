package com.aey.mox.listeners;

import java.util.Optional;

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

    public final boolean isSome() {
        return this.some != null && this.some.isPresent();
    }

    @Override
    public String toString() {
        return "Listener [eventListener=" + eventListener + ", some=" + some + "]";
    }

    

}
