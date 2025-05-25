package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import java.util.ArrayList;

public interface DataController {
    // Interface for dao for customers
    ArrayList<Customer> getAllCustomers();
    Customer getCustomer(int customerID);
    void deleteCustomer(int customerID);
    void saveCustomer (Customer c);
    Customer updateCustomer(int customerID,String name,int age,String email, double balance);
    // DAO Interface for Products
    ArrayList<Product> getAllProducts();
    Product getProduct(int productID);
    void deleteProduct(int productID);
    void saveProduct(Product p);
    Product updateProduct(int productID,String name,int stock,double price);
    void initTables();
    void updateStock(int productID, int newStock);
    //  No deleting purchases, final values
}
