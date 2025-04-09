package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.View.CustomerView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class CustomerController extends BaseController<Customer> {
  public MainController controller;
  public CustomerView view;
  public CustomerController(MainController mc_) {
    super("src/main/java/com/example/javafxassignment1/database/customers.txt");
    this.controller = mc_;
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
	
	private byte[] serializeObject(Customer c){
		try (
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
	){
		oos.writeObject(c);
			return bos.toByteArray();
		} catch (Exception e) {
          e.printStackTrace();
          return null;
		}
	}
    private void save(Customer c) throws SQLException, IOException {
        Connection conn = DataController.getConnection();
        String sql = "INSERT INTO products VALUES (?,?)";
        byte[] serializedObject = serializeObject(c);

        try(conn){
            PreparedStatement ps = conn.prepareStatement(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
