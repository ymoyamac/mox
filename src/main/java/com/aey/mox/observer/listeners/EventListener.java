package com.aey.mox.observer.listeners;

public interface EventListener { // Observe
    <U> void update(final String eventType, U event);
}
