package org.example;

public class Order extends Agent{
    private final int customerID;
    private final int productID;
    public final double cost;

    public Order(int orderID, int _customerID,int _productID,double _cost){
        super(orderID);
        this.customerID = _customerID;
        this.productID = _productID;
        this.cost = _cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerID=" + customerID +
                ", productID=" + productID +
                ", cost=" + cost +
                '}';
    }
}
