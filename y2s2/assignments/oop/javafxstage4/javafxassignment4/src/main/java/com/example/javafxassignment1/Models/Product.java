package com.example.javafxassignment1.Models;

import java.io.*;

public class Product implements Serializable,Comparable<Product>{
    private int id;
    private String name;
    private int stock;
    private double price;
    @Serial
    private static final long serialVersionUID = 1;

    private Product(ProductBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.stock = builder.stock;
        this.price = builder.price;
    }

    public static ProductBuilder builder(int id_,String name_,double price_){
        return new ProductBuilder(id_,name_,price_);
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId(){
        return id;
    }

    public String returnToDb(){ // TODO eval if needed for postgresql
        return getId() + "," + getName()+ "," + this.price + "," + this.stock;
    }
    @Override
    public String toString(){
        return getName() + " € " + this.getPrice();
    }

    @Override
    public int compareTo(Product otherProd) {
        return Double.compare(this.price,otherProd.price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class ProductBuilder {
        private final int id;
        private final String name;
        private int stock;
        private final double price;

        public ProductBuilder(int id_,String name_,double price_){
            this.id = id_;
            this.name = name_;
            this.price = price_;
        }

        public ProductBuilder stock(int stock_){
            this.stock = stock_;
            return this;
        }
        public ProductBuilder inStock(){
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }
}
