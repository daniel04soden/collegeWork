package com.example.javafxassignment1;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Controllers.ProductController;
import com.example.javafxassignment1.View.CustomerView;
import com.example.javafxassignment1.View.MainView;
import com.example.javafxassignment1.View.ProductView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import static com.example.javafxassignment1.View.MainView.applyCSS;

public class MainApplication extends Application {
  // Apply css function
  @Override
  public void start(Stage stage) throws Exception {
    String title = "DS Computing Customer Management Portal";
    stage.setTitle(title);

    CustomerController customerController = new CustomerController();
    ProductController productController = new ProductController();

    BorderPane root = new BorderPane();

    TabPane tabPane = new TabPane();

    // Tabs
    // Product Tab
    Scene productScene = new ProductView(productController).productHome(stage);
    Pane productRoot = (Pane) productScene.getRoot(); // Extract root node
    Tab productTab = new Tab("Products", productRoot);

    // Customer Tab
    Scene customerScene = new CustomerView(customerController).customerHome(stage);
    Pane customerRoot = (Pane) customerScene.getRoot(); // Extract root node
    Tab customerTab = new Tab("Customers", customerRoot);

    // TODO Refactor with borderpanes
    // Purchase Tab
    // Scene purchaseScene = new PurchaseView().purchaseHome(stage);
    // Pane purchaseRoot = (Pane) purchaseScene.getRoot(); // Extract root node
    // Tab purchaseTab = new Tab("Purchases", purchaseRoot);

    tabPane.getTabs().addAll(productTab, customerTab);
    root.setCenter(tabPane);

    Scene home = new Scene(root,1000,500);
    applyCSS(home);
    stage.setScene(home);
    stage.setResizable(false);
    stage.show();
    stage.setOnCloseRequest(event -> {
      event.consume();
       MainView.closeConfirmation(stage);
    });

  }

  public static void main(String[] args) {
    launch();
  }
}
