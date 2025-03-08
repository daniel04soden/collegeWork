package com.example.javafxassignment1.Models;

public class Product extends Agent{
    private int stock;
    private boolean inStock;
    private double price;

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
    @Override
    public String toString(){
        return super.getId() + "," + super.getName()+ "," + this.price+ "," + this.stock+ "," + this.inStock;
    }
}
