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
        Customer currentCustomer = new Customer
                .CustomerBuilder(1,"Dan",12,5000000.00)
                .email("dsoden09@gmail.com")
                .build();
        Product newProduct = new Product
                .ProductBuilder(1,"Computer",200.00)
                .stock(10)
                .inStock()
                .build();
        ArrayList<Product> ap = new ArrayList<>();
        ap.add(newProduct);
        Purchase p = new Purchase(currentCustomer,ap);
        MainController mc = MainController.getMc();
        PurchaseController pc = new PurchaseController(mc);
        assertTrue(pc.confirmPurchase(p));
    }
}