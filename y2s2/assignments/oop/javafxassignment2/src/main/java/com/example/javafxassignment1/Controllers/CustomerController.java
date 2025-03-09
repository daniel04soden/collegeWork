package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CustomerController implements CRUDController{

  private static final String adminPass = "758924"; //TODO make this encrypted
  private final String dbPath;

  public ArrayList<Customer> customers;
  public CustomerController(){
    this.customers = new ArrayList<>();
    this.dbPath = "src/main/java/com/example/javafxassignment1/database/customers.txt";
    loadCustomers();
  }

  @Override
  public void add(String name, String email, int age, double balance) { //TODO reconsider add
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

  @Override
  public void delete(int id,String password) {
    customers.removeIf(customer -> customer.getId() == id);
    //TODO customers.removeIf(customer -> customer.getId() == id && (password.equals(adminPass))); - keep as passfield or?
    printCustomers();
  }

  @Override
  public void load() {
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
        add(name, email, age, balance);
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      System.out.println(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public void save() {
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
  @Override
  public void print(){
    for(Customer customer: customers){
      System.out.println(customer.toString());
    }
  }
}
