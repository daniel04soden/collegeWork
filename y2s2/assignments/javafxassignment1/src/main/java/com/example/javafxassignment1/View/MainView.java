package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class MainView {

    // Custom buttons

    // Back button

    public static Button backBtn(Stage stage, Scene previous) {
        Button backBtn = new Button();
        backBtn.setText("Back");

        backBtn.setOnAction(_ -> stage.setScene(previous));
        return backBtn;
    }
    public static void showCloseConfirmation(Stage stage, ArrayList<Customer> customers) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,);
        alert.setTitle("Save Data?");
        alert.setHeaderText("Do you want to save and exit?");
        alert.initOwner(stage);

        // Saving buttons
        Button save = new Button();
        save.setText("Save and exit");
        save.setOnAction(_ -> {
            CustomerController.saveCustomers(customers);
            CustomerController.printCustomers(customers);

        });

        Button exit = new Button();
        exit.setText("Exit Without saving");
        exit.setOnAction(_ -> {
            CustomerController.saveCustomers(customers);
            CustomerController.printCustomers(customers);

        });
        Button cancel = new Button();


        // This will wait for the user's response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) { // Save no exit
                customers.clear();
                stage.close(); // Close the application if OK is pressed
            } else if (response == ButtonType.YES) { // Save and exit
                    CustomerController.saveCustomers(customers);
                    CustomerController.printCustomers(customers);
            }
        });
    }
}
