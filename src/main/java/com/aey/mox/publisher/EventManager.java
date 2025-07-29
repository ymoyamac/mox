package com.aey.mox.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aey.mox.core.Listener;
import com.aey.mox.listeners.EventBus;
import com.aey.mox.listeners.EventListener;

public class EventManager<T> implements EventBus {
    protected final Map<String, List<Listener<T>>> tableListeners = new HashMap<>();

    public EventManager() {}

    public EventManager(String ...operations) {
        for (String operation : operations) {
            this.tableListeners.put(operation, new ArrayList<>());
        }
    }

    @Override
    public void subscribe(String eventType, EventListener eventListener) {
        List<Listener<T>> e = this.tableListeners.get(eventType);
        e.add(new Listener<>(eventListener));
    }

    @Override
    public void unsubscribe(String eventType, EventListener eventListener) {
        List<Listener<T>> e = this.tableListeners.get(eventType);
        if (e.size() == 0) {
            this.tableListeners.remove(eventType);
            return;
        }
        e.stream()
            .filter(l -> l.getEventListener().equals(eventListener))
            .findFirst()
            .ifPresent(listener -> e.remove(listener));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> void emit(String eventType, EventListener eventListener, U obj) {
        this.tableListeners.get(eventType)
            .stream()
            .filter(l -> l.getEventListener().equals(eventListener))
            .findFirst()
            .ifPresent(listener -> {
                listener.getEventListener().update(eventType, obj);
                listener.setSome((T) obj);
            });
    }

    public Optional<T> ok(String eventType, EventListener eventListener) {
        Optional<Listener<T>> optListener = (Optional<Listener<T>>) this.tableListeners.get(eventType)
            .stream()
            .filter(l -> l.getEventListener().equals(eventListener))
            .findFirst();

        if (optListener.isEmpty()) {
            return Optional.empty();
        }

        return optListener.get().some();
    }

    public boolean isSome(String eventType, EventListener eventListener) {
        return this.tableListeners.get(eventType)
            .stream()
            .filter(l -> l.getEventListener().equals(eventListener))
            .findFirst()
            .isPresent();
    }

    @Override
    public String toString() {
        return "EventManager [listeners=" + tableListeners + "]";
    }
    
}
