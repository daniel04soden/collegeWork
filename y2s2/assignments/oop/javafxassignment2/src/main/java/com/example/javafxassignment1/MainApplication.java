package com.example.javafxassignment1;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.View.CustomerView;
import com.example.javafxassignment1.View.MainView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import static com.example.javafxassignment1.View.MainView.backBtn;

public class MainApplication extends Application {
  // Apply css function
  @Override
  public void start(Stage stage) throws Exception {
    String title = "DS Computing Customer Management Portal";
    stage.setTitle(title);
    CustomerController cntlr = new CustomerController();
    CustomerView startView = new CustomerView(cntlr);
    Scene home = startView.customerHome(stage);
    stage.setScene(home);
    stage.setResizable(false);
    CustomerController customerController = new CustomerController();
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
