package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import com.example.javafxassignment1.View.PurchaseView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Collections;

public class PurchaseController {
    public MainController mc;
    public PurchaseView view;
    private ArrayList<Product> runningCart;
    private ArrayList<Purchase> purchases;

    public PurchaseController(MainController mc_){
       this.mc = mc_;
       this.view = new PurchaseView(this);
       this.runningCart = new ArrayList<Product>();
       this.purchases = new ArrayList<Purchase>();
    }


    // public TextArea displayCurrentPurchases();

    public void sortPurchases(){
        Collections.sort(purchases);
    }

    public ArrayList<Product> getRunningCart() {
        return runningCart;
    }

    public void displayCartInfo(TextArea ta){
        for (Product items: runningCart){
            ta.appendText("\n"+items.toString());
        }

    }

    public void setRunningCart(ArrayList<Product> runningCart) {
        this.runningCart = runningCart;
    }
}
