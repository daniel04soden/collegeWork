
package com.example.javafxassignment1.Models;

import java.util.ArrayList;

public class Order {
    protected int id;
	protected Customer buyingCustomer;
    protected int quantity;
    protected ArrayList<Product> products;
    protected double total;


    public Order(int id_, ArrayList<Product> products_,Customer _customer){
        this.id = id_;
        this.products = products_;
        this.quantity = this.products.size();
        this.total = 0;
		this.buyingCustomer = _customer;
        for (Product product : products_) {
         this.total+=product.getPrice();
        }
    }

	public Customer getBuyingCustomer() {
		return buyingCustomer;
	}

	public void setBuyingCustomer(Customer buyingCustomer) {
		this.buyingCustomer = buyingCustomer;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public ArrayList<Product> getProducts() {
		return products;
	}

	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}

}
