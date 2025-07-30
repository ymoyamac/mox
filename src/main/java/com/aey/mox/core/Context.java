package com.aey.mox.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.aey.mox.publisher.EventManager;

/**
 * You can subscribe multiple {@code EventListeners} to the same {@code Context} with
 * different actions, and you can also subscribe multiple {@code EventListeners} to
 * the same {@code Context} with the same action. This means that
 * {@code EventListeners} can be grouped by the same action, but listen to different
 * events and respond to different events.
 * 
 * <p>
 * 
 * This happens because the {@code Context} has an internal {@code HashMap} that is
 * responsible for grouping lists of {@code EventListeners} by action. And each list
 * can contain different {@code EventListeners}. Therefore, if your
 * {@code EventListeners} are registered in a different list with a different action,
 * they will not be automatically updated every time you call the {@code emit()}
 * function. But you can update all the {@code EventListeners} in a list by simply
 * calling the {@code emit()} function. If you want to update a specific event
 * {@code EventListener}, you will have to call the {@code notify()} function, passing
 * the event type and the event listener as parameters (this way, the {@code Context}
 * will know that you only want to update a specific {@code EventListener}).
 * 
 * 
 */
public class Context<T, E> extends EventManager {

    private final Map<String, Prop<?>> props = new HashMap<>();
    private E err;

    public Context(String ...operations) {
        super(operations);
    }

    public final Map<String, Prop<?>> getProps() {
        return this.props;
    }

    public final void set(String key, T value) {
        Prop<T> prop = Prop.bind(key, value);
        this.props.put(key, prop);
    }

    public final void set(Prop<?> ...props) {
        for (Prop<?> prop : props) {
            this.props.put(prop.getId(), prop);
        }
    }

    @SuppressWarnings("unchecked")
    public final <U> Optional<U> get(String name) {
        Prop<?> prop = props.get(name);

        if (prop == null) {
            return Optional.empty();
        }

        if (!prop.getClazz().isInstance(prop.getValue())) {
            throw new ClassCastException("Expected type " + prop.getClazz() + ", but got " + prop.getValue().getClass());
        }
        return Optional.ofNullable((U) prop.getValue());
    }

    public final E err(E err) {
        this.err = err;
        return err;
    }

    @Override
    public String toString() {
        return "Context [props=" + props + ", tableListeners=" + super.tableListeners + ", err=" + err + "]";
    };

}
