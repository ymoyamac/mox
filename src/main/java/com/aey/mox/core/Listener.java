package com.aey.mox.core;

import java.util.Optional;

import com.aey.mox.listeners.EventListener;

/**
 * {@code Listener} is a lightweight wrapper around an {@link EventListener} that also stores
 * the last value it received during a notification.
 * 
 * It is used internally by the {@code EventManager} to keep track of both the
 * registered listener and the last event payload associated with it.
 * 
 * @author ymoyamac
 * @since 1.0
 */
public class Listener {

    private EventListener eventListener;

    /**
     * Stores the last payload sent to this listener, if any.
     */
    private Optional<?> some;

    /**
     * Creates a new {@code Listener} instance that wraps the given {@link EventListener}.
     *
     * @param eventListener The listener to wrap
     * 
     * @since 1.0
     */
    public Listener(EventListener eventListener) {
        this.eventListener = eventListener;
        this.some = Optional.empty();
    }

    /**
     * Returns the wrapped {@link EventListener}.
     *
     * @return {@link EventListener} The event listener instance
     * 
     * @since 1.0
     */
    public EventListener getEventListener() {
        return this.eventListener;
    }

    /**
     * Sets the last payload received by this listener.
     *
     * @param   <T> The type of the payload 
     * @param   obj The value to store
     * 
     * @since 1.0
     */
    public final <T> void setSome(T obj) {
        this.some = Optional.ofNullable(obj);
    }

    /**
     * Returns the last payload received by this listener, if available.
     *
     * @return {@link Optional} Containing the last value, or empty if none exists
     * 
     * @since 1.0
     */
    public final Optional<?> some() {
        return this.some;
    }

    @Override
    public String toString() {
        return "Listener {eventListener=" + eventListener + ", some=" + some + "}";
    }

}
