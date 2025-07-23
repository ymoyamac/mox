package com.aey.mox.observer.listeners;

public interface EventBus { // Subject
    <T> void subscribe(String eventType, EventListener<T> listener);
    <T> void unsubscribe(String eventType, EventListener<T> listener);
    <T> void emit(T event);
}
