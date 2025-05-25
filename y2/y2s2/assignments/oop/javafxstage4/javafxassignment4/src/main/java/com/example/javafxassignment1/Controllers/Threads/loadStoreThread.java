package com.example.javafxassignment1.Controllers.Threads;

import com.example.javafxassignment1.Controllers.MainController;
import com.example.javafxassignment1.Models.Store;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class loadStoreThread extends Thread{
    private Store store;

    public loadStoreThread(Store s){
       this.store = s;
    }

    @Override
   public void run(){
         store = null;
         try{
             FileInputStream fio = new FileInputStream("src/main/java/com/example/javafxassignment1/database/store.ser");
             ObjectInputStream stream = new ObjectInputStream(fio);
             store = (Store) stream.readObject();
             stream.close();
             fio.close();
             System.out.println("Main Instance loaded");
         } catch (ClassNotFoundException | IOException e) {
             throw new RuntimeException(e);
         }
    }
}
