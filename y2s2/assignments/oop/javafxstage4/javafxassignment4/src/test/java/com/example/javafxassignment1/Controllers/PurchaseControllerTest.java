package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseControllerTest {
    @Test
    void confirmPurchase() {
        Customer c = new Customer(1,"Dan","dsoden09@gmail.com",21,250.00);
        Product pr = new Product(1,"Computer",10,200.00);
        ArrayList<Product> ap = new ArrayList<>();
        ap.add(pr);
        Purchase p = new Purchase(c,ap);
        MainController mc = MainController.getMc();
        PurchaseController pc = new PurchaseController(mc);
        assertTrue(pc.confirmPurchase(p));
    }
}