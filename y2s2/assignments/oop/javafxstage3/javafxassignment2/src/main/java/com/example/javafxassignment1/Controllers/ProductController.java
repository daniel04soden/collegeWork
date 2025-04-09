package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.View.ProductView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

}
