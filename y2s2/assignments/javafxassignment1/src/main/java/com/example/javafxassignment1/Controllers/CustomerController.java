package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CustomerController {

  private static final String dbPath = "src/main/java/com/example/javafxassignment1/database/customers.txt";

  public void addCustomer(ArrayList<Customer> customers,String name, String email, int age, double balance) {
    int newId;
    if (customers.isEmpty()){
      newId = 1;
    }else{
      newId = customers.getLast().getId()+1;
    }
    Customer currentCustomer = new Customer(newId, name, email, age, balance);
    customers.add(currentCustomer);
    System.out.println("New Customer:" + currentCustomer);
    System.out.println("All customers\n");
    System.out.println("-----------------\n");
    for (Customer customer : customers) {
      System.out.println(customer.toString() + "\n");
    }
  }

  public void deleteCustomer(int id,ArrayList<Customer> customers) {
    customers.removeIf(customer -> customer.getId() == id);
    printCustomers(customers);
  }

  public void loadCustomers(ArrayList<Customer> customers) {
    File dbFile = new File(dbPath);
    try (Scanner customerReader = new Scanner(dbFile)){
      customers.clear();
      while (customerReader.hasNextLine()) {
        String data = customerReader.nextLine();
        String[] customerInfo = data.split(",");
        String name = customerInfo[1].trim();
        int age = Integer.parseInt(customerInfo[2].trim());
        String email = customerInfo[3].trim();
        double balance = Double.parseDouble(customerInfo[4].trim());
        addCustomer(customers,name, email, age, balance);
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      System.out.println(Arrays.toString(e.getStackTrace()));
    }
  }


  public static void saveCustomers(ArrayList<Customer> customers) {
    try (FileWriter saver = new FileWriter(dbPath)) {
      for (Customer customer : customers) {
        saver.write(customer.toString() + System.lineSeparator());
      }
      System.out.println("Saved to " + dbPath);
    } catch (IOException e) {
      System.out.println("Error saving to file, try again");
      e.printStackTrace();
    }
  }

  public static void printCustomers(ArrayList<Customer> customers){
    for(Customer customer: customers){
      System.out.println(customer.toString());
    }
  }
}
