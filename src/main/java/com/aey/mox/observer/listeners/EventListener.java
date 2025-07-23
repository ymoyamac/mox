package com.aey.mox.observer.listeners;

public interface EventListener<T> { // Observe
    void update(final String eventType, T event);
}
