package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;

import java.io.*;
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
    public abstract ArrayList<T> getStorage();

    // Same reusable method for saving to postgres
   public void save(){
       try (FileOutputStream saver = new FileOutputStream(dbPath)) {
           ObjectOutputStream os = new ObjectOutputStream(saver);
           os.writeObject(storage);
           System.out.println("Storage Array: "+storage);
           System.out.println("Saving storage to file");

           System.out.println("Saved to " + dbPath);
       } catch (IOException e) {
           System.out.println("Error saving to file, try again");
           e.printStackTrace();
       }
   };

   // TODO - Eval if load needed with postgres
    public void load(){
        System.out.println("Log current names: " + storage);
        storage.clear();
        try {
            FileInputStream fio = new FileInputStream(dbPath);
            ObjectInputStream stream = new ObjectInputStream(fio);
            storage = (ArrayList<T>) stream.readObject(); // Read only one object
            stream.close();
            fio.close();
            System.out.println(storage);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(storage);
        }
    } // Abstract method for loading object
    public void print() { // Common print method
        for (T object : storage) {
            System.out.println(object.toString());
        }
    }

}
