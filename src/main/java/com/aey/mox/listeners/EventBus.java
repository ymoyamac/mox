package com.aey.mox.listeners;

public interface EventBus { // Subject
    void subscribe(String eventType, EventListener listener);
    void unsubscribe(String eventType, EventListener listener);
    <T> void notify(String eventType, EventListener eventListener, T obj);
    <T> void emit(String eventType, T obj);
}
