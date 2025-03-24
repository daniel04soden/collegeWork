package com.example.javafxassignment1.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class Purchase implements Serializable{
    private ArrayList<Product> cart;
    private Customer buyer;
    private final Date timeOfPurchase;
    @Serial
    private static final long serialVersionUID = 1;


    public Purchase(Customer buyingCustomer,ArrayList<Product> cart_){
        this.buyer = buyingCustomer;
        this.cart = cart_;
        this.timeOfPurchase = new Date();
    }

    public double calcTotal(){
        double total = 0;
        for (Product products: cart){
            total+=products.getPrice();
        }
        return total;
    }

    public Date getTimeOfPurchase() {
        return timeOfPurchase;
    }

    // Separate date and time getters, mainly used to get them as a string
    public String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.timeOfPurchase);
    }

    public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(this.timeOfPurchase);
    }

    public boolean confirmPurchase(){
        boolean outcome = false;
        boolean inStock = true;
        double total = calcTotal();
        for (Product products: cart){
            if (products.getStock() < 1) {
                inStock = false;
                break;
            }
        }
        if (!(buyer.getBalance() < total) && inStock){
            for (Product products: cart){
                int currentStock = products.getStock();
                products.setStock(currentStock - 1);
            }
            buyer.setBalance(buyer.getBalance()-total);
            return true;
        }
        return outcome;
    }

    public Customer getBuyer() {
        return buyer;
    }

    public void setBuyer(Customer buyer) {
        this.buyer = buyer;
    }


    @Override
    public String toString(){
        return "Buyer: " + buyer.toString() + " bought " + cart.size() + " items for a total of €" + calcTotal()
                + " \nDate/Time of purchase " + getDate() + "\t" + getTime();
    }

    // public int compareTo(Purchase other) {
        // return this.timeOfPurchase.compareTo(other.getTimeOfPurchase());
    // }
}
