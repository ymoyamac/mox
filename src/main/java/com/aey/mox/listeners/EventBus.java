package com.aey.mox.listeners;

/**
 * The {@code EventBus} interface defines the contract for a publish-subscribe (pub-sub)
 * event system based on the Observer design pattern. It allows listeners to subscribe
 * to specific event types and be notified when events are published.
 * 
 * @author ymoyamac
 * @since 1.0
 */
public interface EventBus { // Subject
    /**
     * Registers a listener to receive notifications for a specific event type.
     *
     * @param eventType the name or identifier of the event
     * @param listener  the listener to subscribe
     * 
     * @since 1.0
     */
    void subscribe(String eventType, EventListener listener);

    /**
     * Unregisters a listener from a specific event type.
     *
     * @param eventType the name or identifier of the event
     * @param listener  the listener to unsubscribe
     * 
     * @since 1.0
     */
    void unsubscribe(String eventType, EventListener listener);

    /**
     * Notifies a specific listener of an event. Useful when targeting a single listener
     * instead of broadcasting to all.
     *
     * @param eventType      the name of the event
     * @param eventListener  the specific listener to notify
     * @param obj            the payload associated with the event
     * @param <T>            the type of the event payload
     * 
     * @since 1.0
     */
    <T> void notify(String eventType, EventListener eventListener, T obj);

    /**
     * Emits an event to all registered listeners for the given event type.
     *
     * @param eventType The name of the event
     * @param obj       The payload to deliver
     * @param <T>       The type of the payload
     */
    <T> void emit(String eventType, T obj);
}
