package com.aey.mox.observer.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aey.mox.observer.listeners.EventBus;
import com.aey.mox.observer.listeners.EventListener;

public class EventManager implements EventBus {

    private Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {}

    public EventManager(String ...operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    @Override
    public <T> void subscribe(String eventType, EventListener listener) {
        List<EventListener> e = this.listeners.get(eventType);
        e.add(listener);
    }

    @Override
    public <T> void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> e = this.listeners.get(eventType);
        e.remove(listener);
    }

    @Override
    public <T> void emit(String eventType, T obj) {
        List<EventListener> e = this.listeners.get(eventType);
        for (EventListener listener : e) {
            listener.update(eventType, obj);
        }
    }
    
}
