// Created by nsgwick for nsgwick. (https://www.nsgwick.com/)
package com.nsgwick.nconfig;

public class Item<T> {

    private Class<T> c;

    private T object;

    public Item() {}

    public Item(final T object, final Class<T> type) {
        this.c = type;
        this.object = object;
    }

    public Item(final Class<T> c, final T value) {
        setClass(c);
        set(value);
    }

    public T get() {
        return c.cast(this.object);
    }

    public void set(final T value) {
        this.object = value;
    }

    public void setClass(Class<T> c) {
        this.c = c;
    }

    public Class<T> getC() {
        return c;
    }
}
