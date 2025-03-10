package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class PurchaseController {

    private ArrayList<Product> onGoingCart;

    public PurchaseController(){
    }

    public void makePurchase(Customer c){
        Purchase purchase = new Purchase(onGoingCart,c);
        double total = purchase.getTotal();
        if (c.getBalance() < total){
            System.out.println("Not enough money in account");
            return new Label("Insufficient funds");
        }
        c.setBalance(c.getBalance() - total);
        for (Product products: cart){
            int currentStock = products.getStock();
            products.setStock(currentStock- 1);
        }
        return new Label("Customer" + c.getName() + " Paid: "+total + "for " + cart.size() + " items");
    }

    public void addToCart(Product p){
       onGoingCart.add(p);
    }

}