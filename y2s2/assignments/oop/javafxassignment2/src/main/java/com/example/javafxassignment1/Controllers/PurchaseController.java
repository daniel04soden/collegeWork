package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import com.example.javafxassignment1.View.PurchaseView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Collections;

public class PurchaseController extends BaseController<Purchase>{
    public MainController mc;
    public PurchaseView view;
    private ArrayList<Product> runningCart;
    private ArrayList<Purchase> purchases;

    public PurchaseController(MainController mc_){
        super("src/main/java/com/example/javafxassignment1/database/Purchases.txt");
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

    /**
     * @param params
     */
    @Override
    public void add(String... params) {

    }

    /**
     * @param id
     */
    @Override
    public void delete(int id) {

    }

    /**
     *
     */
    @Override
    public void load() {

    }

    /**
     * @return
     */
    @Override
    public ArrayList<Purchase> getStorage() {
        return null;
    }

    /**
     *
     */
    @Override
    public void save() {

    }
}
