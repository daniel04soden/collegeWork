package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import com.example.javafxassignment1.View.PurchaseView;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;

public class PurchaseController{
    public MainController mc;
    public PurchaseView view;
    private ArrayList<Product> runningCart;
    private ArrayList<Purchase> purchases;

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    private final String dbPath = "src/main/java/com/example/javafxassignment1/database/Purchases.txt";

    public PurchaseController(MainController mc_){
        this.mc = mc_;
       this.view = new PurchaseView(this);
       this.runningCart = new ArrayList<Product>();
       this.purchases = new ArrayList<Purchase>();
    }


    // public TextArea displayCurrentPurchases();

    public void sortPurchases(){
       // Collections.sort(purchases);
    }

    public ArrayList<Product> getRunningCart() {
        return runningCart;
    }

    public void displayCartInfo(TextArea ta){
        for (Product items: runningCart){
            ta.appendText("\n"+items.toString());
        }

    }

    public void recordPurchase(Purchase p) {
        purchases.add(p);
        System.out.println(p);
    }

    public void loadInPurchases(){
       purchases.clear();
       try {
           FileInputStream fio = new FileInputStream(dbPath);
           ObjectInputStream stream = new ObjectInputStream(fio);
           purchases = (ArrayList<Purchase>) stream.readObject(); // Read only one object
           stream.close();
           fio.close();
           System.out.println(purchases);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void savePurchase(){
        try (FileOutputStream saver = new FileOutputStream(dbPath)) {
            ObjectOutputStream os = new ObjectOutputStream(saver);
            os.writeObject(purchases);
            System.out.println("Purchases: "+purchases);
            System.out.println("Saving purchases to file");

            System.out.println("Saved to " + dbPath);
        } catch (IOException e) {
            System.out.println("Error saving to file, try again");
            e.printStackTrace();
        }
    }

}
