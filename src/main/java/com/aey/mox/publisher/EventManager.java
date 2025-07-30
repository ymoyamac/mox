package com.aey.mox.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aey.mox.core.Listener;
import com.aey.mox.listeners.EventBus;
import com.aey.mox.listeners.EventListener;

/**
 * The {@code EventManager} manages its own state while also maintaining a map
 * of list of {@code Listeners} and notifying them of state changes by calling their
 * function {@code update()} operation. The responsibility of {@code Listeners} is
 * to register and unregister themselves with a {@code EventManager} (in order to be
 * notified of state changes) and to update their state (to synchronize it with the
 * subject's state) when they are notified.
 */
public class EventManager implements EventBus {
    protected final Map<String, List<Listener>> tableListeners = new HashMap<>();

    public EventManager() {}

    public EventManager(String ...operations) {
        for (String operation : operations) {
            this.tableListeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Internally to the {@code EventManager}, the function {@code subscribe} does not
     * invoke a new execution that delivers values. It simply registers the given
     * {@code EventListener} in a list of {@code Listeners} which in turn groups them
     * on a map according to their type of action.
     * 
     * @param eventType     Event action
     * @param eventListener The listener that subscribes to the {@code EventManager}
     */
    @Override
    public void subscribe(String eventType, EventListener eventListener) {
        List<Listener> e = this.tableListeners.get(eventType);
        e.add(new Listener(eventListener));
    }

    @Override
    public void unsubscribe(String eventType, EventListener eventListener) {
        List<Listener> e = this.tableListeners.get(eventType);
        if (e.size() == 0) {
            this.tableListeners.remove(eventType);
            return;
        }
        e.stream()
            .filter(l -> l.getEventListener().equals(eventListener))
            .findFirst()
            .ifPresent(listener -> e.remove(listener));
    }

    /**
     * When a {@code EventManager} changes state, all registered {@code Listeners} are
     * notified and updated automatically.
     * 
     * @param eventType     Event action
     * @param eventListener The listener that subscribes to the {@code EventManager}
     */
    @Override
    public <U> void notify(String eventType, EventListener eventListener, U obj) {
        this.tableListeners.get(eventType)
            .stream()
            .filter(l -> l.getEventListener().equals(eventListener))
            .findFirst()
            .ifPresent(listener -> {
                listener.getEventListener().update(eventType, obj);
                listener.setSome(obj);
            });
    }

    @Override
    public <U> void emit(String eventType, U obj) {
        this.tableListeners.get(eventType)
            .forEach(listener -> {
                listener.getEventListener().update(eventType, obj);
                listener.setSome(obj);
            });
    }

    public Optional<?> ok(String eventType, EventListener eventListener) {
        Optional<Listener> optListener = (Optional<Listener>) this.tableListeners.get(eventType)
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
