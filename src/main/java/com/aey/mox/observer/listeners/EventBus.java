package com.aey.mox.observer.listeners;

public interface EventBus { // Subject
    <T> void subscribe(String eventType, EventListener listener);
    <T> void unsubscribe(String eventType, EventListener listener);
    <T> void emit(String eventType, T obj);
}
