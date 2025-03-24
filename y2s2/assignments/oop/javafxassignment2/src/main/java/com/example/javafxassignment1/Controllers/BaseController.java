package com.example.javafxassignment1.Controllers;

import java.util.ArrayList;

public abstract class BaseController<T>{
    protected final String dbPath;
    protected ArrayList<T> storage;

    public BaseController(String dbPath) {
        this.dbPath = dbPath;
        this.storage = new ArrayList<>();
        load();
    }

    public abstract void add(String... params); // Abstract method for adding an object
    public abstract void delete(int id); // Abstract method for deleting an object
    public abstract void load(); // Abstract method for loading object
    public abstract ArrayList<T> getStorage();
   public abstract void save(); // Abstract method for saving object
    public void print() { // Common print method
        for (T object : storage) {
            System.out.println(object.toString());
        }
    }
}
