package com.example.javafxassignment1.Controllers.Threads;

import com.example.javafxassignment1.Models.Store;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class saveStoreThread extends Thread{
    private final Store storeInstance;

    public saveStoreThread(Store s){
        this.storeInstance =s;
    }

    @Override
    public void run(){
        try(FileOutputStream saver = new FileOutputStream("src/main/java/com/example/javafxassignment1/database/store.ser")) {
            ObjectOutputStream os = new ObjectOutputStream(saver);
            os.writeObject(storeInstance);
            System.out.println("Saved store instance");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

}
