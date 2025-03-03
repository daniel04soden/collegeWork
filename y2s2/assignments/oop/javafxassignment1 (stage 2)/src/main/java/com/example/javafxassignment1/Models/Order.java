package com.example.javafxassignment1.Models;

import java.util.ArrayList;

public class Order extends Agent{ //TODO Extend from record

    private final ArrayList<Product> products;
    private final int total;

    public Order(ArrayList<Product> _products, int _total,String _name, int _id){
        super(_id,_name);
        this.total = _products.forEach(product.price -> total+=product.price); // Loop over and calculate total with price

        this.products = _products;
        generateReceipt();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getTotal() {
        return total;
    }

    private void generateReceipt(Customer customer,ArrayList<Product> products){
       /*
       * This function will take customer ids, product ids and
       * other information about the order and create a receipt
       * in the constructor
       * The idea of it running at construction time is subject to change
       *
       * */
    }
}
