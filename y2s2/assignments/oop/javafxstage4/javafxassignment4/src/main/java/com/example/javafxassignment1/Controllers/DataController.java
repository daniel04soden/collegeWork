package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataController {
    Customer getCustomer(int customerID);
    void deleteCustomer(int customerID);
    //  No deleting purchases, final values
    void saveCustomer (Customer c);
    Customer updateCustomer(int customerID);
    Connection establishConnection() throws SQLException;
}
