package org.example;


public class Product extends Agent{

    public double price;
    protected int currentStock;
    protected int amountOnOrder;
    protected  boolean discontinued = false;


    public Product(int productId, double _price, int _currentStock, int _amountOnOrder){
        super(productId);
        this.price = _price;
        this.currentStock = _currentStock;
        this.amountOnOrder = _amountOnOrder;
        if (this.amountOnOrder < 1){
           this.discontinued = true;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    // No setter for setting current stock, that will be implemented as follows:


    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getAmountOnOrder() {
        return amountOnOrder;
    }

    public void setAmountOnOrder(int amountOnOrder) {
        this.amountOnOrder = amountOnOrder;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }
}
