package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {

    @Test
    @DisplayName("Testing adding a customer to the db")
    void add() {
        MainController mc = new MainController();
        // All attributes strings as done in the implementation

        String age = "12";
        String name = "Daniel Soden";
        String email = "dsoden09@gmail.com";
        String balance = "12.2";
        CustomerController cc = new CustomerController(mc);
        int id = cc.getStorage().size();
        Customer testCustomer = new Customer(id+1, name, email, Integer.parseInt(age), Double.parseDouble(balance));
        cc.add(name, email, age, balance);
        assertEquals(cc.getStorage().getLast().getId(),testCustomer.getId());
    }

    @Test
    @DisplayName("Testing deleting a customer")
    void delete() {
        MainController mc = new MainController();
        CustomerController cc = new CustomerController(mc);
        int originalSize = cc.getStorage().size();
        cc.delete(cc.getStorage().getLast().getId());
        int newSize = cc.getStorage().size();
        assertNotEquals(originalSize, newSize);
    }
}
