// Created by nsgwick for nsgwick. (https://www.nsgwick.com/)
package com.nsgwick.nconfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NConfig {

    private String path;

    private List<Item> items = new ArrayList<>();

    public NConfig() {}

    public NConfig(final String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }

    public void addItem(final Item item) {
        items.add(item);
    }

    public void addItem(final Object item) {
        addItem(new Item(item.getClass(), item));
    }

    public void setItem(final int i, final Object item) {
        items.set(i, new Item(item.getClass(), item));
    }

    public void setItem(final int i, final Item item) {
        items.set(i, item);
    }

    public Item getItem(final int index) {
        return items.get(index);
    }

    public List<Item> getItemsOf(final Class c) {
        return items.stream().filter(i -> i.getC().equals(c)).collect(Collectors.toList());
    }

    public int findMatching(final Item item) {
        if(!items.contains(item)) return -1;
        return items.indexOf(item);
    }

    public List<Item> getAllMatching(final Item item) {
        return items.stream().filter(i -> i.equals(item)).collect(Collectors.toList());
    }

    public int countMatching(final Item item) {
        return getAllMatching(item).size();
    }

    public int size() {
        return items.size();
    }

    public void clear() {
        this.items = new ArrayList<>();
    }

    public boolean save() {
        File f = new File(path);
        try {
            f.createNewFile();
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(path, false));
            for(Item i : items) {
                objOut.writeObject(i.get());;
            }
            objOut.writeObject(null);
            objOut.flush();
            objOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean load() {
        File f = new File(path);
        try {
            if(!f.exists()) return false;
            clear();
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Object obj = objIn.readObject();
            while(obj != null) {
                addItem(obj);
                obj = objIn.readObject();
            }
            objIn.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
