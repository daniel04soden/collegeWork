package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Order;
import com.example.javafxassignment1.Models.Product;

import java.util.ArrayList;

public class PurchaseController {


    protected ArrayList<Customer> customers;
    protected ArrayList<Product> cart;
    private String purchasePath = "src/main/java/com/example/javafxassignment1/database/products.txt";
    public void ProductController(){
    }

    public Order makePurchase(Order order,Customer buyer){

    }

    public String getPurchasePath() {
        return purchasePath;
    }

    public void setPurchasePath(String purchasePath) {
        this.purchasePath = purchasePath;
    }
}
