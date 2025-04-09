package com.example.javafxassignment1.Models;

import java.io.*;

public class Product extends Agent implements Serializable,Comparable<Product>{
    private int stock;
    private boolean inStock;
    private double price;
    @Serial
    private static final long serialVersionUID = 1;

    public Product(){
        super();
    }

    public Product(int _id, String _name,int _stock,double _price){
        super(_id,_name);
        this.stock = _stock;
        this.price = _price;
        this.inStock = stock > 0;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId(){
        return super.getId();
    }

    public String returnToDb(){
        return super.getId() + "," + super.getName()+ "," + this.price+ "," + this.stock+ "," + this.inStock;
    }
    @Override
    public String toString(){
        return super.getName() + " € " + String.valueOf(this.getPrice());
    }

    @Override
    public int compareTo(Product otherProd) {
        return Double.compare(this.price,otherProd.price);
    }
}
