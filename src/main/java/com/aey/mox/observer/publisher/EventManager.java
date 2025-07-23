package com.aey.mox.observer.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aey.mox.observer.listeners.EventBus;
import com.aey.mox.observer.listeners.EventListener;

public class EventManager implements EventBus {

    Map<String, List<EventListener<?>>> listeners = new HashMap<>();

    public EventManager(String ...operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    @Override
    public <T> void subscribe(String eventType, EventListener<T> listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }

    @Override
    public <T> void unsubscribe(String eventType, EventListener<T> listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unsubscribe'");
    }

    @Override
    public <T> void emit(T event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notify'");
    }
    
}
