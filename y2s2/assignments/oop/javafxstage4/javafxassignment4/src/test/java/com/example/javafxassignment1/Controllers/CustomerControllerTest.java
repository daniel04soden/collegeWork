package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {

    @Test
    @DisplayName("Testing adding a customer to the db")
    void add() {
        // All attributes strings as done in the implementation

        String age = "12";
        String name = "Daniel Soden";
        String email = "dsoden09@gmail.com";
        String balance = "12.2"; // As strings as this reflects in real scenarios
        CustomerController cc = CustomerController.getC(MainController.getMc());
        int id = cc.getStorage().size()+1;
        Customer currentCustomer = new Customer
                .CustomerBuilder(id,name,Integer.parseInt(age),Double.parseDouble(balance))
                .email(email)
                .build();
        cc.add(name, email, age, balance);
        assertEquals(cc.getStorage().getLast().getId(),currentCustomer.getId());
    }

    @Test
    @DisplayName("Testing deleting a customer")
    void delete() {
        CustomerController cc = CustomerController.getC(MainController.getMc());
        int originalSize = cc.getStorage().size();
        cc.delete(cc.getStorage().getLast().getId());
        int newSize = cc.getStorage().size();
        assertNotEquals(originalSize, newSize);
    }
}
