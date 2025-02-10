package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private List<Customer> customers= new ArrayList<>();

    public void addCustomer(String name, String email,int age){
        int newId = customers.size()+1;
        Customer currentCustomer = new Customer(newId,name,email,age);
        customers.add(currentCustomer);
        System.out.println("Added customer" + currentCustomer.getName() + currentCustomer.getId());
    }

    public void deleteCustomer(int id){
        customers.removeIf(customer -> customer.getId() == id);
    }

    public void loadCustomers(String txtFile){
        try {
            File myObj = new File(txtFile);
            Scanner customerReader = new Scanner(myObj);
            while (customerReader.hasNextLine()) {
                String data = customerReader.nextLine();
                String[] customerInfo = data.split(",");
                String name = customerInfo[1];
                String email = customerInfo[2];
                int age = Integer.parseInt(customerInfo[3]);
                addCustomer(name,email,age);
            }
            customerReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    }
