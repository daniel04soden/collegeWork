package com.example.javafxassignment1.Controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ThreadStoreSave {
   public synchronized boolean saveComputerStore() {
    try {
      FileOutputStream computerStore =
              new FileOutputStream("src/main/java/com/example/javafxassignment1/database/store.ser");
      ObjectOutputStream outputStream = new ObjectOutputStream(computerStore);
      outputStream.writeObject(MainController.getMc());
      outputStream.close();
      computerStore.close();
      return true;
    }  catch (IOException e) {
        e.printStackTrace();
        return false;
    }
   }
}
