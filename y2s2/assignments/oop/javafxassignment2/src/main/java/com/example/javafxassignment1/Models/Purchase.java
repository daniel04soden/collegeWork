package com.example.javafxassignment1.Models;

import java.util.ArrayList;

public class Purchase {
    private double total;
    private final ArrayList<Product> cart;
    private final Customer buyer;
    public Purchase( ArrayList<Product> cart_, Customer buyer_){
       this.cart = cart_;
       this.buyer = buyer_;
       for (Product product: cart){
           total+=product.getPrice();
       }
    }

    public Customer getBuyer() {
        return buyer;
    }
    public ArrayList<Product> getCart() {
        return cart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    // Extra functionality

}
