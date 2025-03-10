package com.example.javafxassignment1;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Controllers.MainController;
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
    MainController mc = new MainController();

    CustomerController customerController = new CustomerController(mc);
    ProductController productController = new ProductController(mc);

    BorderPane root = new BorderPane();


    Scene home = mc.prc.view.mainPurchase(stage);
    applyCSS(home);
    stage.setScene(home);
    stage.setResizable(false);
    stage.show();
    stage.setOnCloseRequest(event -> {
      event.consume();
      mc.view.closeConfirmation(stage);
    });

  }

  public static void main(String[] args) {
    launch();
  }
}
