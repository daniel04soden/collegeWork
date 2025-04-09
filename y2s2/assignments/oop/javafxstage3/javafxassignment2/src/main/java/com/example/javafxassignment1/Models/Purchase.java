package com.example.javafxassignment1.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.io.*;

public class Purchase implements Serializable,Comparable<Purchase>{
  private ArrayList<Product> cart;
  private Customer buyer;
  private int customerID;
  private Date timeOfPurchase;
  private double total;
  @Serial
  private static final long serialVersionUID = 1;


  public Purchase(Customer buyingCustomer, ArrayList<Product> cart_) {
    this.buyer = buyingCustomer;
    this.cart = cart_;
    this.customerID = buyer.getId();
    this.timeOfPurchase = new Date();
    for (Product product : cart_) {
     total+=product.getPrice();
    }
  }


  public Date getTimeOfPurchase() {
    return timeOfPurchase;
  }

  // Separate date and time getters, mainly used to get them as a string
  public String getDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    return formatter.format(this.timeOfPurchase);
  }

  public String getTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
    return formatter.format(this.timeOfPurchase);
  }

  public int getCustomerID() {
    return customerID;
  }

  public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }

  public void setCart(ArrayList<Product> cart) {
    this.cart = cart;
  }

  public Customer getBuyer() {
    return buyer;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setBuyer(Customer buyer) {
    this.buyer = buyer;
  }

  public ArrayList<Product> getCart() {
    return cart;
  }
  @Override
  public String toString() {
    return buyer.getName() + " bought " + cart.size() + " items for a total of €" + getTotal()
        + " \nDate/Time of purchase " + getDate() + "\t" + getTime();
  }
  @Override
  public int compareTo(Purchase p) {
    return this.getTimeOfPurchase().compareTo(p.getTimeOfPurchase());
  }

  public static Comparator<Purchase> customerPriceComparator = new Comparator<Purchase>() {
    @Override
    public int compare(Purchase p1, Purchase p2) {
      return Double.compare(p2.getTotal(),p1.getTotal());
    }
  };
}
