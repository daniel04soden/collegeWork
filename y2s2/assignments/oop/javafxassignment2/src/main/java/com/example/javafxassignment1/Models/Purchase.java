package com.example.javafxassignment1.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class Purchase implements java.io.Serializable{
    private ArrayList<Product> cart;
    private Customer buyer;
    private LocalDate purchaseDate;

    public Purchase(Customer buyingCustomer,ArrayList<Product> cart_){
        this.buyer = buyingCustomer;
        this.cart = cart_;
        this.purchaseDate = LocalDate.from(LocalDateTime.now());
    }

    public double calcTotal(){
        double total = 0;
        for (Product products: cart){
            total+=products.getPrice();
        }
        return total;
    }
    public String getDate(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDate = this.purchaseDate.format(myFormatObj);
        return formatDate;
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
                + "Date/Time of purchase " + getDate();
    }
}
