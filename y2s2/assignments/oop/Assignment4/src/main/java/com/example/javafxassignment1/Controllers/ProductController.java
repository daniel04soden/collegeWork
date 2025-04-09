package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.View.ProductView;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class ProductController extends BaseController<Product> {
    public MainController controller;
    public ProductView view;
    public ProductController(MainController mc_) {
        super("src/main/java/com/example/javafxassignment1/database/products.txt");
        this.controller = mc_;
        this.view = new ProductView(this);
    }

    @Override
    public void add(String... params) {
        String name = params[0];
        int stock = Integer.parseInt(params[1]);
        double price = Double.parseDouble(params[2]);
        int newId;
        if (storage.isEmpty()) {
            newId = 1;
        } else {
            newId = storage.getLast().getId() + 1; // Get the last product's ID
        }
        Product newProduct = new Product(newId, name, stock, price);
        storage.add(newProduct);
        System.out.println("New Product: " + newProduct);
        print(); // Print all products
    }

    @Override
    public void delete(int id) {
            storage.removeIf(product -> product.getId() == id);
            print();
    }

    @Override
    public ArrayList<Product> getStorage(){
        return storage;
    }

    private byte[] serializeObject(Product p){
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
        ){
            oos.writeObject(p);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void save(Product p) throws SQLException, IOException {
        Connection conn = DataController.getConnection();
        String sql = "INSERT INTO products VALUES (?,?)";
        byte[] serializedObject = serializeObject(p);

        try(conn){
            PreparedStatement ps = conn.prepareStatement(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
