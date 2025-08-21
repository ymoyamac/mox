package com.aey.mox.uni;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.aey.mox.core.Prop;
import com.aey.mox.listeners.Observe;
import com.aey.mox.listeners.Subject;

public class Context<T, E> implements Subject {
    private final Map<String, Prop<?>> props = new HashMap<>();
    private final Map<String, Observe> listeners = new HashMap<>();
    private E err;

    /**
     * Returns the complete {@code Map} of all props stored in the context. This
     * data structure contains all the properties registered by all listeners.There are
     * no access restrictions and they are visible to everyone.
     * 
     * @return {@code Map<String, Prop<?>>} The properties of the context
     * 
     * @since 1.0
     */
    public final Map<String, Prop<?>> getProps() {
        return this.props;
    }

    /**
     * /**
     * Stores a typed value in the context under the given key. This method internally
     * creates a {@link Prop} instance with the {@code bind()} static method.
     *
     * @param   key     The key under which the value is stored 
     * @param   value   The value to store
     * 
     * @since 1.0
     */
    public final void set(String key, T value) {
        Prop<T> prop = Prop.bind(key, value);
        this.props.put(key, prop);
    }

    /**
     * Stores one or more {@link Prop} instances in the context. This method uses the
     * static {@code bind()} method of the {@link Prop} class to create a new instance.
     *
     * @param props  One or more properties to store
     * 
     * @since 1.0
     */
    public final void set(Prop<?> ...props) {
        for (Prop<?> prop : props) {
            this.props.put(prop.getId(), prop);
        }
    }

    /**
     * Retrieves a property value by its key, cast to the expected type.
     * Throws {@link ClassCastException} if the actual type of the stored value
     * does not match the type stored in the corresponding {@link Prop}.
     *
     * @param   name    The key of the property to retrieve 
     * @param   <U>     The expected type of the value 
     * @return  {@link Optional} 
     *          Containing the value if present and type matches, or empty otherwise
     * 
     * @throws  ClassCastException
     *          If the type of the stored value does not match
     * 
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public final <U> Optional<U> get(String name) {
        Prop<?> prop = props.get(name);

        if (prop == null) {
            return Optional.empty();
        }

        return Optional.ofNullable((U) prop.getValue());
    }

    public final E err(E err) {
        this.err = err;
        return this.err;
    }

    @Override
    public void subscribe(String event, Observe listener) {
        this.listeners.put(event, listener);
    }

    @Override
    public void unsubscribe(String event) {
        this.listeners.remove(event);
    }

    @Override
    public <U> void emit(String event, U obj) {
        Optional<Observe> listener = Optional.ofNullable(this.listeners.get(event));
        listener.ifPresent(observe -> observe.update(obj));
    }


}
