package com.example.javafxassignment1.Models;

import java.util.ArrayList;

public class Receipt extends Record{
    private final ArrayList<Product> products;
    private double total;

    public Receipt(int _id, String _description,ArrayList<Product> _products){
        super(_id,_description);
        this.products = _products;
        this.total =
        this.total = 1.0;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
