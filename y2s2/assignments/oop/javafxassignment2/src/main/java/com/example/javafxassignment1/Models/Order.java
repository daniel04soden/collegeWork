
package com.example.javafxassignment1.Models;

import java.util.ArrayList;

public class Order {
    protected int id;
    protected int quantity;
    protected ArrayList<Product> products;
    protected double total;


    public Order(int id_, ArrayList<Product> products_){
        this.id = id_;
        this.products = products_;
        this.quantity = this.products.size();
        this.total = 0;
        for (Product product : products_) {
         this.total+=product.getPrice();
        }
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
