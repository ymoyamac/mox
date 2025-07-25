package com.aey.mox.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aey.mox.listeners.EventBus;
import com.aey.mox.listeners.EventListener;

public class EventManager implements EventBus {
    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    // este campo ok debe de estar envuelto en una clase que contenga `List<EventListener>` y el `ok`
    // por que si existe más de un listenner el ok se va a sobreescribir
    // crear una clase `Listener` para que se vea así
    // ```
    // public class Listener {
    //      private List<EventListener>
    //      private Optional<T> ok
    // }```
    // `private final Map<String, Listener> listeners = new HashMap<>();`
    private Optional<Object> ok;

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
        // aquí sobreescribiría el valor de `ok` a null, "todos los listeners comparten el ok
        // como si fuera un Singleton"
        this.ok = null;
    }

    @Override
    public <U> void emit(String eventType, U obj) {
        List<EventListener> e = this.listeners.get(eventType);
        for (EventListener listener : e) {
            listener.update(eventType, obj);
            this.ok = Optional.of(obj);
        }
    }

    
    public final boolean isSome() {
        return this.ok != null && this.ok.isPresent();
    }

    public Optional<Object> ok() {
        return this.ok;
    }

    @Override
    public String toString() {
        return "EventManager [listeners=" + listeners + "]";
    }

    
}
