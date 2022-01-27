// Created by nsgwick for nsgwick. (https://www.nsgwick.com/)
package com.nsgwick.nconfig;

public class Item<T> {

    private Class<T> c;

    private Object object;

    public Item() {}

    public Item(final Object object, final Class<T> type) {
        this.c = type;
        this.object = object;
    }

    public Item(final Class<T> c, final Object value) {
        setClass(c);
        set(value);
    }

    public T get() {
        return c.cast(this.object);
    }

    public void set(final Object value) {
        if(c.isInstance(value)) this.object = value;
        else System.out.println("Casting Error! Please report this to a developer.");;
    }

    public void setClass(Class<T> c) {
        this.c = c;
    }

    public Class<T> getC() {
        return c;
    }
}
