package com.example.javafxassignment1.Controllers.Threads;

import com.example.javafxassignment1.Controllers.MainController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class loadStoreThread extends Thread{
    private MainController mainInstance;

    public loadStoreThread(MainController mc){
       this.mainInstance = mc;
    }

    @Override
   public void run(){
         mainInstance = null;
         try{
             FileInputStream fio = new FileInputStream("src/main/java/com/example/javafxassignment1/database/store.ser");
             ObjectInputStream stream = new ObjectInputStream(fio);
             mainInstance = (MainController) stream.readObject();
             stream.close();
             fio.close();
             System.out.println("Main Instance loaded");
         } catch (ClassNotFoundException | IOException e) {
             throw new RuntimeException(e);
         }
    }
}
