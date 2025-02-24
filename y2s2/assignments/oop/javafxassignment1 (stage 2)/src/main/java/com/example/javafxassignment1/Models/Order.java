package com.example.javafxassignment1.Models;

import java.util.ArrayList;

public class Order extends Agent{

    private final ArrayList<Product> products; // Products will contain the functions for calcualting the total
    private final int total;

    public Order(ArrayList<Product> _products, int _total,String _name, int _id){
        super(_id,_name);
        this.total = _total; // Potentially replace this with a total calculation in the constructor
        this.products = _products;
        generateReceipt();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getTotal() {
        return total;
    }

    private void generateReceipt(){
       /*
       * This function will take customer ids, product ids and
       * other information about the order and create a receipt
       * in the constructor
       * The idea of it running at construction time is subject to change
       *
       * */
    }
}
