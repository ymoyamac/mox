package com.aey.mox.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.aey.mox.publisher.EventManager;

public class Context<T, E> extends EventManager {

    private final Map<String, Prop<?>> props = new HashMap<>();
    private E err;

    public Context(String ...operations) {
        super(operations);
    }

    public EventManager events() {
        return this;
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
