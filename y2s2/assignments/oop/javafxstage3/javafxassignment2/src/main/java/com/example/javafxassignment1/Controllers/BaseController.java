package com.example.javafxassignment1.Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.*;

public abstract class BaseController<T>{
    /*
      This controller contains many constant methods for the various controllers
      and when need be, these methods can be overriden for specific implementation
     */

    protected final String dbPath;  // Remove from here, will all be pointed to within datacontroller
    protected ArrayList<T> storage; // Generic Storage, in specific extensions, specified

    public BaseController(String dbPath) {
        this.dbPath = dbPath;
        this.storage = new ArrayList<>();
        load();
    }

    public abstract void add(String... params); // Abstract method for adding an object
    public void delete(int id) {
    }
    public void load(){
        System.out.println("Attempting to load"+storage);
        storage.clear();
        try {
            FileInputStream fio = new FileInputStream(dbPath);
            ObjectInputStream stream = new ObjectInputStream(fio);
            storage = (ArrayList<T>)stream.readObject();
            fio.close();
            stream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Loading product error");
        }
    }; // Abstract method for loading object
    public abstract ArrayList<T> getStorage();
    public void save(){
        try (FileOutputStream saver = new FileOutputStream(dbPath)) {
            ObjectOutputStream os = new ObjectOutputStream(saver);
            os.writeObject(storage);
            System.out.println("Storage"+storage);
            System.out.println("Saving products to file");

            System.out.println("Saved to " + dbPath);
        } catch (IOException e) {
            System.out.println("Error saving to file, try again");
            e.printStackTrace();
        }
    }; // Abstract method for saving object
    public void print() { // Common print method
        for (T object : storage) {
            System.out.println(object.toString());
        }
    }
}
