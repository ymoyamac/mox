package com.aey.mox.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.aey.mox.examples.entities.ErrorCodes;
import com.aey.mox.examples.entities.MocaErr;


public abstract class Context<T, E extends Err> {

    private final Map<String, Prop<?>> props = new HashMap<>();
    private final Supplier<E> errFactory;
    private Optional<T> ok;
    private E err;

    protected Context(Supplier<E> errFactory) {
        this.errFactory = errFactory;
    }

    public Map<String, Prop<?>> getProps() {
        return this.props;
    }

    public final void set(String key, T value) {
        Prop<T> prop = Prop.bind(key, value);
        this.props.put(key, prop);
    }
    
    public final void set(Prop<?> prop) {
        props.put(prop.getId(), prop);
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
        return Optional.of((U) prop.getValue());
    }

    public final boolean isSome() {
        return this.ok != null && this.ok.isPresent();
    }

    public final void result(String id) {
        Optional<T> value = this.get(id);
        this.ok = value;
    }

    public final Optional<T> ok() {
        return this.ok;
    }

    public final void setErr(ErrorCodes errorCodes) {
        E e = errFactory.get();
        e.setCode(errorCodes.getCode());
        e.setMsg(errorCodes.getMsg());

        // Solo si es una subclase con errContext
        if (e instanceof MocaErr mocaErr) {
            mocaErr.setErrContext(errorCodes.getErrContext());
        }

        this.err = e;
        this.ok = Optional.empty();
    }

    public final E err() {
        return this.err;
    }

}
