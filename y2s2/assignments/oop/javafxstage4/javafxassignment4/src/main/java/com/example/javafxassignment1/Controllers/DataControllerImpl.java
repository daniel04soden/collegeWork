package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;

public class DataControllerImpl implements DataController{
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final DataControllerImpl d = new DataControllerImpl(url);
    private DataControllerImpl(String url_){ // Singleton pattern

    }

    public DataControllerImpl getD() {
        return d;
    }

    @Override
    public Customer getCustomer(int customerID) {
        return null;
    }

    @Override
    public Product getProduct(int productID) {
        return null;
    }

    @Override
    public Purchase getPurchase(int purchaseID) {
        return null;
    }

    @Override
    public void deleteCustomer(int customerID) {

    }

    @Override
    public void deleteProduct(int customerID) {

    }

    @Override
    public void saveCustomer(Customer c) {

    }

    @Override
    public void saveProduct(Product p) {

    }

    @Override
    public void savePurchase(Purchase prc) {

    }

    @Override
    public Customer updateCustomer(int customerID) {
        return null;
    }

    @Override
    public Product updateProduct(int productID) {
        return null;
    }
    // DAO Design pattern
}
