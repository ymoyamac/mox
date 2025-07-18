package com.aey.mox.core;

public class Prop<T> {

    private String id;
    private T value;
    private Class<T> clazz;

    private Prop(String id, T value, Class<T> clazz) {
        this.id = id;
        this.value = value;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Class<T> getClazz() {
        return this.clazz;
    }

    @SuppressWarnings("unchecked")
    public final static <T> Prop<T> bind(String id, T value) {
        return new Prop<>(id, value, (Class<T>) value.getClass());
    }

    @Override
    public String toString() {
        return "Prop{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }
}
