package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;

public interface DataController {
    Customer getCustomer(int customerID);
    Product getProduct(int productID);
    Purchase getPurchase(int purchaseID);
    void deleteCustomer(int customerID);
    void deleteProduct(int customerID);
    //  No deleting purchases, final values
    void saveCustomer (Customer c);
    void saveProduct(Product p);
    void savePurchase(Purchase prc);
    Customer updateCustomer(int customerID);
    Product updateProduct(int productID);
}
