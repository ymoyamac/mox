package com.aey.mox.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Context<T, E> {

    protected final Map<String, Prop<?>> props = new HashMap<>();
    protected Optional<T> ok;
    protected E err;

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

    public final boolean isSome() {
        return this.ok != null && this.ok.isPresent();
    }

    public final void result(T ok) {
        this.ok = Optional.of(ok);
    }

    public final Optional<T> ok() {
        return this.ok;
    }

    public final E err(E err) {
        this.err = err;
        return err;
    };


}
