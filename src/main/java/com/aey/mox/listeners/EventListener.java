package com.aey.mox.listeners;

public interface EventListener { // Observe
    <U> void update(final String eventType, U event);
}
