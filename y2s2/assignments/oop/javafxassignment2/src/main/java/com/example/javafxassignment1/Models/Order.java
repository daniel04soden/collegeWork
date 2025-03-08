
package com.example.javafxassignment1.Models;

import java.util.ArrayList;

public class Order {
    protected int id;
    protected int quantity;
    protected ArrayList<Product> products;
    protected double price;


    public Order(int id_, ArrayList<Product> products_){
        this.id = id_;
        this.products = products_;
        this.quantity = this.products.size();
        this.price = 2.0; //TODO change to non-placeholder
    }

    // Getters

    // Setters

    // Omit idSetter, non-editable value

    // Extra functionality
}
