package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import com.example.javafxassignment1.View.PurchaseView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PurchaseController implements Serializable{

  public MainController mc;
  public PurchaseView view;
  private ArrayList<Product> runningCart;
  private ArrayList<Purchase> purchases;

  private final String dbPath = "src/main/java/com/example/javafxassignment1/database/Purchases.txt";

  public PurchaseController(MainController mc_) {
    this.mc = mc_;
    this.view = new PurchaseView(this);
    this.runningCart = new ArrayList<Product>();
    this.purchases = new ArrayList<Purchase>();
  }

  public ArrayList<Product> getRunningCart() {
    return runningCart;
  }

  public void displayPurchaseInfo(TextArea ta,boolean forCart) {
    ta.clear();
    if (forCart){
      for (Product items : runningCart) {
        ta.appendText("\n" + items.toString());
      }
    }else{
      for (Purchase purchase : purchases) {
        ta.appendText("\n" + purchase.toString());
      }
    }

  }

  public void execSort(TextArea t, boolean byDate) {
    if (byDate) {
      Collections.sort(purchases);
      displayPurchaseInfo(t,false);
    } else {
      Collections.sort(runningCart);
      displayPurchaseInfo(t,true);
    }
  }

  public void execSort(TextArea t) {
    t.clear();
    purchases.sort(Purchase.customerPriceComparator);
    displayPurchaseInfo(t,false);
  }

  public void searchForOrderById(TextArea t,int id){
    for (Purchase purchase : purchases) {
      if (Objects.equals(purchase.getBuyer().getId(), id)) {
        t.clear();
        t.appendText(purchase.toString());
      }else{
        t.clear();
        t.appendText("No purchase found");
      }
    }
  }

  public void recordPurchase(Purchase p) {
    purchases.add(p);
    System.out.println(p);
  }

  public void loadInPurchases() {
    if (MainController.checkFile(dbPath)){
      purchases.clear();
      try {
        FileInputStream fio = new FileInputStream(dbPath);
        ObjectInputStream stream = new ObjectInputStream(fio);
        purchases = (ArrayList<Purchase>) stream.readObject(); // Read only one object
        stream.close();
        fio.close();
        System.out.println(purchases);
      } catch (IOException | ClassNotFoundException e ) {
        String error = e.toString();
        System.out.println(error);
      }
    }else{
     System.out.println("File not found or is empty");
    }
  }

  public void savePurchase() {
    if (MainController.checkFile(dbPath)){
      try (FileOutputStream saver = new FileOutputStream(dbPath)) {
        ObjectOutputStream os = new ObjectOutputStream(saver);
        os.writeObject(purchases);
        System.out.println("Purchases: " + purchases);
        System.out.println("Saving purchases to file");

        System.out.println("Saved to " + dbPath);
      } catch (IOException e) {
        System.out.println("Error saving to file, try again");
        e.printStackTrace();
      }
    }else{
      System.out.println("Issue with file");
    }
  }

  public boolean confirmPurchase(Purchase p) {
     Customer c = p.getBuyer();
    double total = p.getTotal();
    List<Product> cart = p.getCart();
    for (Product product : cart) {
        if (product.getStock() < 1) {
            System.out.println("Product " + product.getName() + " is out of stock. Please try again.");
            return false;
        }
    }
    if (c.getBalance() < total) {
        System.out.println("Insufficient balance.");
        return false;
    }
    for (Product product : cart) {
        int currentStock = product.getStock();
        mc.dbController.updateStock(product.getId(), currentStock - 1);
    }

    double newBalance = c.getBalance() - total;
    c.setBalance(newBalance);
    mc.dbController.updateBalance(c.getId(), newBalance);

    return true;
  }

  public ArrayList<Purchase> getPurchases() {
    return purchases;
  }

  public void setPurchases(ArrayList<Purchase> purchases) {
    this.purchases = purchases;
  }

  public void checkFromBox(ComboBox<Product> box, Label stockDisplay){
    String res;
    Product selectedValue = box.getValue();
    if (selectedValue != null){
      res = String.valueOf(selectedValue.getStock());
    }else{
      res = "";
    }
    stockDisplay.setText(res);
  }

}
