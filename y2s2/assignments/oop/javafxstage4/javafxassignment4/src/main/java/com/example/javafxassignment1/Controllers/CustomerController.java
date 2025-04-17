package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.View.CustomerView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class CustomerController extends BaseController<Customer>{
  public static MainController controller;
  public CustomerView view;

  private static final CustomerController c = new CustomerController(controller);
  private CustomerController(MainController mc_) {
    super("src/main/java/com/example/javafxassignment1/database/customers.txt");
    controller = mc_;
    this.view = new CustomerView(this);
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
    Customer currentCustomer = new Customer
            .CustomerBuilder(newId,name,age,balance)
            .email(email)
            .build();

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
    System.out.println("Log current names: " + storage);
    storage.clear();
    try {
      FileInputStream fio = new FileInputStream(dbPath);
      ObjectInputStream stream = new ObjectInputStream(fio);
      storage = (ArrayList<Customer>) stream.readObject(); // Read only one object
      stream.close();
      fio.close();
      System.out.println(storage);
    }
    catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println(storage);
    }
  }

  @Override
  public void save() {
    try (FileOutputStream saver = new FileOutputStream(dbPath)) {
        ObjectOutputStream os = new ObjectOutputStream(saver);
        os.writeObject(storage);
        System.out.println("Storage Array: "+storage);
        System.out.println("Saving customers to file");

      System.out.println("Saved to " + dbPath);
    } catch (IOException e) {
      System.out.println("Error saving to file, try again");
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<Customer> getStorage(){
    return storage;
  }
  public void balanceAdj(TextField t, ComboBox<Customer> cb){
    double amountAdding = Double.parseDouble(t.getText());
    Customer customer = cb.getValue();
    System.out.println("Old" + customer.getName() + " balance €" + customer.getBalance());
    customer.setBalance(customer.getBalance()+amountAdding);
    System.out.println("New" + customer.getName() + " balance €" + customer.getBalance());
    t.clear();
  }

  // Needed for singleton pattern
  public static CustomerController getC() {
    return c;
  }

}
