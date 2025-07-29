package com.aey.mox.listeners;

public interface EventBus { // Subject
    void subscribe(String eventType, EventListener listener);
    void unsubscribe(String eventType, EventListener listener);
    <T> void emit(String eventType, EventListener eventListener, T obj);
}
