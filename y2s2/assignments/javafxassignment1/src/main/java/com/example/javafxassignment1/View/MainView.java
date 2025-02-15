package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainView {

    // Custom buttons

    // Back button

    public static Button backBtn(Stage stage, Scene previous) {
        // Simple repeating button that stops repeating of code, plan on implementing more in Stage 2
        Button backBtn = new Button();
        backBtn.setText("Back");

        backBtn.setOnAction(_ -> stage.setScene(previous));
        return backBtn;
    }
    public static void closeConfirmation(Stage stage, ArrayList<Customer> customers) {
        // Function to prevent instant closing and check if the user has saved or not
        ButtonType saveAndExit = new ButtonType("Save and Exit?");
        ButtonType saveNoExit = new ButtonType("Don\'t save?");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);



        System.out.println("Attempting to close window");
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("You sure you want to quit?");
        closeAlert.getButtonTypes().setAll(saveAndExit,saveNoExit,cancel);

        ButtonType finalChoice = closeAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (finalChoice == saveAndExit){
            CustomerController.saveCustomers(customers);
            CustomerController.printCustomers(customers);
            stage.close();
        }else if (finalChoice == saveNoExit){
            stage.close();
        }
    }
}
