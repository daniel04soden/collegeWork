package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DataController {
    // Interface for dao
    ArrayList<Customer> getAllCustomers();
    Customer getCustomer(int customerID);
    void deleteCustomer(int customerID);
    //  No deleting purchases, final values
    void saveCustomer (Customer c);
    Customer updateCustomer(int customerID,String name,int age,String email, double balance);
}
