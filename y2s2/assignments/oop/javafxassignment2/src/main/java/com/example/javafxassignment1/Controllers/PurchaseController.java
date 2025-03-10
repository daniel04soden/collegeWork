package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.View.PurchaseView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class PurchaseController {
    public MainController mc;
    public PurchaseView view;
    private ArrayList<Product> runningCart;

    public PurchaseController(MainController mc_){
       this.mc = mc_;
       this.view = new PurchaseView(this);
       this.runningCart = new ArrayList<Product>();
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
