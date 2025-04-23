package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.View.ProductView;

import java.io.*;
import java.util.ArrayList;

public class ProductController extends BaseController<Product>{
    public static MainController controller;
    public ProductView view;

    private static ProductController pc;
    private ProductController(MainController mc_) {
        super("src/main/java/com/example/javafxassignment1/database/products.txt");
        controller = mc_;
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
        Product newProduct = new Product
                .ProductBuilder(newId,name,price)
                .stock(stock)
                .inStock()
                .build();
        storage.add(newProduct);
        controller.dbController.saveProduct(newProduct);
        System.out.println("New Product: " + newProduct);
        print(); // Print all products
    }

    @Override
    public void delete(int id) {
            print();
            storage.removeIf(product -> product.getId() == id);
            controller.dbController.deleteProduct(id);
    }

    @Override
    public void load() {
        System.out.println("Logging current prodNames: "+storage);
        storage.clear();
        try {
            FileInputStream fio = new FileInputStream(dbPath);
            ObjectInputStream stream = new ObjectInputStream(fio);
            storage = (ArrayList<Product>)stream.readObject();
            fio.close();
            stream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Loading product error");
        }
    }

    @Override
    public void save() {
        try (FileOutputStream saver = new FileOutputStream(dbPath)) {
            ObjectOutputStream os = new ObjectOutputStream(saver);
            os.writeObject(storage);
            System.out.println("Storage Of Products: "+storage);
            System.out.println("Saving products to file");

            System.out.println("Saved to " + dbPath);
        } catch (IOException e) {
            System.out.println("Error saving to file, try again");
            e.printStackTrace();
        }
    }
    public static ProductController getP(MainController mc) {
        if (pc == null) {
            pc = new ProductController(mc);
        }
        return pc;
    }
    @Override
    public ArrayList<Product> getStorage(){
        return storage;
    }

    public void loadProductsFromDb(){
        storage = controller.dbController.getAllProducts();
        System.out.println("Loaded all products from sql db, overwritten from serialised dataset");
    }
}
