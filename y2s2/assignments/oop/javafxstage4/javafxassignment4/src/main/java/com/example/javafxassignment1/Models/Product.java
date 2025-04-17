package com.example.javafxassignment1.Models;

import java.io.*;

public class Product implements Serializable,Comparable<Product>{
    private int id;
    private String name;
    private int stock;
    private boolean inStock;
    private double cost;
    @Serial
    private static final long serialVersionUID = 1;

    private Product(ProductBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.stock = builder.stock;
        this.cost = builder.cost;
        this.inStock = builder.inStock;
    }

    public int getStock() {
        return stock;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
        return id;
    }

    public String returnToDb(){ // TODO eval if needed for postgresql
        return getId() + "," + getName()+ "," + this.cost + "," + this.stock+ "," + this.inStock;
    }
    @Override
    public String toString(){
        return getName() + " € " + this.getCost();
    }

    @Override
    public int compareTo(Product otherProd) {
        return Double.compare(this.cost,otherProd.cost);
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
        private int id;
        private String name;
        private int stock;
        private boolean inStock;
        private double cost;

        public ProductBuilder(int id_,String name_,double cost_){
            this.id = id_;
            this.name = name_;
            this.cost = cost_;
        }

        public ProductBuilder stock(int stock_){
            this.stock = stock_;
            return this;
        }
        public ProductBuilder inStock(){
            this.inStock = this.stock >0;
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }
}
