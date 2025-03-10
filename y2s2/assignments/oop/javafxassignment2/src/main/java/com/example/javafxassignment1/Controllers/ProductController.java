package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.View.ProductView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public void load() {
        File dbFile = new File(dbPath);
        try (Scanner productReader = new Scanner(dbFile)) {
            storage.clear();
            while (productReader.hasNextLine()) {
                String data = productReader.nextLine();
                String[] productInfo = data.split(",");
                String name = productInfo[1].trim();
                String price = productInfo[2].trim(); // Will be parsed on add
                String stock = productInfo[3].trim();
                add(name, stock, price);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void save() {
        try (FileWriter saver = new FileWriter(dbPath)) {
            for (Product product : storage) {
                saver.write(product.returnToDb() + System.lineSeparator());
                System.out.println(product.getId()+product.getName());
            }
            System.out.println("Saved to " + dbPath);
        } catch (IOException e) {
            System.out.println("Error saving to file, try again");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> getStorage(){
        return storage;
    }

}
