package com.aey.mox.listeners;

public interface Observe {
    /**
     * Called when the subject emits an event the listener is subscribed to.
     *
     * @param event     the event payload
     * @param <U>       the type of the payload
     */
    <U> void update(U event);
}
