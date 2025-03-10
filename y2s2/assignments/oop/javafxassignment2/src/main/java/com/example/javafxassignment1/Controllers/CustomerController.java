package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CustomerController extends BaseController<Customer> {
  public MainController controller;
  public CustomerController(MainController mc_) {
    super("src/main/java/com/example/javafxassignment1/database/customers.txt");
    this.controller = mc_;
  }

  @Override
  public void add(String... params) {
    String name = params[0];
    String email = params[1];
    int age = Integer.parseInt(params[2]);
    double balance = Double.parseDouble(params[3]);
    int newId;
    if (storage.isEmpty()) {
      newId = 1;
    } else {
      newId = storage.getLast().getId() + 1;
    }
    Customer currentCustomer = new Customer(newId, name, email, age, balance);
    storage.add(currentCustomer);
    System.out.println("New Customer: " + currentCustomer);
    print(); // Print all customers
  }

  @Override
  public void delete(int id) {
    storage.removeIf(customer -> customer.getId() == id);
    print(); // Print remaining customers
  }

  @Override
  public void load() {
    File dbFile = new File(dbPath);
    try (Scanner customerReader = new Scanner(dbFile)) {
      storage.clear();
      while (customerReader.hasNextLine()) {
        String data = customerReader.nextLine();
        String[] customerInfo = data.split(",");
        String name = customerInfo[1].trim();
        String age = customerInfo[2].trim();
        String email = customerInfo[3].trim();
        String balance = customerInfo[4].trim();
        add(name, email, age, balance);
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred, file not found");
      System.out.println(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public void save() {
    try (FileWriter saver = new FileWriter(dbPath)) {
      for (Customer customer : storage) {
        saver.write(customer.returnToDb() + System.lineSeparator());
      }
      System.out.println("Saved to " + dbPath);
    } catch (IOException e) {
      System.out.println("Error saving to file, try again");
      e.printStackTrace();
    }
  }
  @Override
  public ArrayList<Customer> getStorage(){
    return storage;
  };
}
