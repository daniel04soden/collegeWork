package com.example.javafxassignment1.Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Controllers.MainController;
import com.example.javafxassignment1.Controllers.ProductController;

public class Store implements Serializable{
    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    private ArrayList<Purchase> purchases;
    private static Store storeSingle;

    @Serial
    private static final long serialVersionUID = 1;

    private Store(MainController mc){
        customers = CustomerController.getC(mc).getStorage();
        products = ProductController.getP(mc).getStorage();
        purchases = mc.prc.getPurchases();
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Purchase> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    public static Store getStore(MainController mc){
        if (storeSingle == null) {
            storeSingle = new Store(mc);
        }
        return storeSingle;
    }

    // Overload the getStore method for subsequent calls
    public static Store getStore() {
        if (storeSingle == null) {
            throw new IllegalStateException("No maincontroller");
        }
        return storeSingle;
    }
}