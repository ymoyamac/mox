package com.aey.mox.core;

import java.util.Map;
import java.util.Optional;

public class Result<T, E> {

    private final Map<String, Prop<?>> props;
    private Class<T> clazz;
    private T ok;
    //private E err;

    public Result(Map<String, Prop<?>> props, Class<T> clazz) {
        this.props = props;
        this.clazz = clazz;
    }    

    @SuppressWarnings("unchecked")
    public Optional<T> ok(String id) {
        Prop<?> prop = props.get(id);
        if (prop == null) {
            return Optional.empty();
        }
        if (!this.clazz.isInstance(prop.getValue())) {
            throw new ClassCastException("Expected type " + clazz + ", but got " + prop.getValue().getClass());
        }
        this.ok = (T) prop.getValue();

        return Optional.of(this.ok);
    }

}