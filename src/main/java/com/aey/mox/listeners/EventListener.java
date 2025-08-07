package com.aey.mox.listeners;

/**
 * The {@code EventListener} interface represents the observer in the Observer pattern.
 * Implementing classes must define how they respond to an event notification.
 */
public interface EventListener { // Observe

    /**
     * Called when the subject emits an event the listener is subscribed to.
     *
     * @param eventType the name or identifier of the event
     * @param event     the event payload
     * @param <U>       the type of the payload
     */
    <U> void update(final String eventType, U event);
}
