package com.aey.mox.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aey.mox.listeners.EventBus;
import com.aey.mox.listeners.EventListener;

public class EventManager implements EventBus {
    protected final Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {}

    public EventManager(String ...operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    @Override
    public <U> void subscribe(String eventType, EventListener listener) {
        List<EventListener> e = this.listeners.get(eventType);
        e.add(listener);
    }

    @Override
    public <U> void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> e = this.listeners.get(eventType);
        e.remove(listener);
    }

    @Override
    public <U> void emit(String eventType, U obj) {
        List<EventListener> e = this.listeners.get(eventType);
        for (EventListener listener : e) {
            listener.update(eventType, obj);
        }
    }

    @Override
    public String toString() {
        return "EventManager [listeners=" + listeners + "]";
    }

    
}
