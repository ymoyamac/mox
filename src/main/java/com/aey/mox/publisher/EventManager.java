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
 * of list of {@link Listener} and notifying them of state changes by calling their
 * function {@code update()} operation. The responsibility of {@link Listener} is
 * to register and unregister themselves with a {@code EventManager} (in order to be
 * notified of state changes) and to update their state (to synchronize it with the
 * subject's state) when they are notified.
 * 
 * @author ymoyamac
 * @since 1.0
 */
public class EventManager implements EventBus {
    protected final Map<String, List<Listener>> tableListeners = new HashMap<>();
    protected String[] actions;

    /**
     * Default empty constructor
     * 
     * @since 1.0
     */
    public EventManager() {}
    
    /**
     * Constructs a {@code EventManager} and initializes the internal map with
     * predefined actions, each associated with an empty list of {@link Listener}.
     *
     * @param actions The actions (event types) to register
     * 
     * @since 1.0
     */
    public EventManager(String ...actions) {
        for (String action : actions) {
            this.tableListeners.put(action, new ArrayList<>());
        }
        this.actions = this.tableListeners.keySet().toArray(new String[0]);
    }

    /**
     * Registers a new action (event type) in the manager, initializing its listener
     * list.
     *
     * @param action The event type to register
     * 
     * @since 1.0
     */
    public final void setAction(String action) {
        this.tableListeners.put(action, new ArrayList<>());
    }

    public final String[] actions() {
        return this.actions;
    }

    /**
     * Internally to the {@code EventManager}, the function {@code subscribe} does not
     * invoke a new execution that delivers values. It simply registers the given
     * {@link EventListener} in a list of {@link Listener} which in turn groups them
     * on a map according to their type of action.
     * 
     * Subscribes a given {@link EventListener} to the specified event type.
     * Does not emit any event or value at registration time.
     * 
     * @param   eventType       The type of event to listen for 
     * @param   eventListener   The listener that subscribes to the {@link EventManager}
     * 
     * @since 1.0
     */
    @Override
    public void subscribe(String eventType, EventListener eventListener) {
        List<Listener> e = this.tableListeners.get(eventType);
        e.add(new Listener(eventListener));
    }

    /**
     * Unsubscribes the given {@link EventListener} from the specified event type.
     * If the listener list becomes empty, the action is removed from the map.
     *
     * @param   eventType       The event type to unsubscribe from 
     * @param   eventListener   The listener to remove
     * 
     * @since 1.0
     */
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
     * When a {@code EventManager} changes state, all registered {@link Listener} are
     * notified and updated automatically.
     * Notifies a specific {@link EventListener} of an event of the given type,
     * sending a payload object.
     *
     * @param   <U>             The type of the payload 
     * @param   eventType       The type of event to trigger 
     * @param   eventListener   The specific listener to notify 
     * @param   obj             The payload or object associated with the event
     * 
     * @since 1.0
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

    /**
     * Broadcasts an event to all listeners subscribed under the given event type,
     * sending a payload to each.
     *
     * @param   <U>         The type of the payload 
     * @param   eventType   The type of event to broadcast 
     * @param   obj         The payload or object to send to each listener
     * 
     * @since 1.0
     */
    @Override
    public <U> void emit(String eventType, U obj) {
        this.tableListeners.get(eventType)
            .forEach(listener -> {
                listener.getEventListener().update(eventType, obj);
                listener.setSome(obj);
            });
    }

    /**
     * Retrieves the last known value received by a specific listener
     * for the given event type, if available.
     *
     * @param   eventType       The type of event 
     * @param   eventListener   The listener to query 
     * @return  {@link Optional}
     *          Containing the last known value or empty if none exists
     * 
     * @since 1.0
     */
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

    /**
     * Checks whether a specific listener has a registered value and has also been
     * registered under the specified event type.
     *
     * @param   eventType       The event type 
     * @param   eventListener   The listener to check 
     * @return  {@code true} if registered, {@code false} otherwise
     * 
     * @since 1.0
     */
    public boolean isSome(String eventType, EventListener eventListener) {

        Optional<Listener> listener = this.tableListeners.get(eventType)
            .stream()
            .filter(l -> l.getEventListener().equals(eventListener))
            .findFirst();

        if (listener.isEmpty()) {
            return false;
        }

        return listener.get()
            .some()
            .isPresent();
    }

    @Override
    public String toString() {
        return "EventManager {listeners=" + tableListeners + "}";
    }
    
}
