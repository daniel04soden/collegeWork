package com.example.javafxassignment1;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
public class MainApplication extends Application {
    String customerLocation = "./database/customers.txt";

    public Scene homePage(BorderPane root){
        // Init the scene

        Scene home = new Scene(root,1000,500);

        // Header Bar
        Label title= new Label();
        title.setText("DS Computing");

        // Customer Register button
        Button register = new Button();
        register.setText("Register");

        // View Customers button
        Button view = new Button();
        view.setText("View other customers");

        // Remove Customers button

        Button remove = new Button();
        remove.setText("Remove Customers");

        // Load data button
        Button load = new Button();
        load.setText("Load Customer Data");

        // Positioning the Components

        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        VBox vertical = new VBox(titleBar,register,view,remove,load);
        vertical.setSpacing(25);
        vertical.setAlignment(Pos.CENTER);

        root.setCenter(vertical);

        return home;
    }

    public Scene registerPage(BorderPane root){
        // Init the scene

        Scene register = new Scene(root,1000,500);

        // Header Bar
        Label title= new Label();
        title.setText("DS Computing: Register an account");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);

        // Name Hbox label and input
        Label nameLabel = new Label("Name:");
        TextField nameInput = new TextField();
        HBox nameBlock = new HBox(nameLabel,nameInput);
        nameBlock.setAlignment(Pos.CENTER);

        // Age hbox label and input

        Label ageLabel = new Label("Age:");
        TextField ageInput = new TextField();
        HBox ageBlock = new HBox(ageLabel,ageInput);
        ageBlock.setAlignment(Pos.CENTER);

        // Email hbox label and input

        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();
        HBox emailBlock = new HBox(emailLabel,emailInput);
        emailBlock.setAlignment(Pos.CENTER);

        // Balance hbox label and input

        Label balanceLabel = new Label("Balance:");
        TextField balanceInput = new TextField();
        HBox balanceBlock = new HBox(balanceLabel,balanceInput);
        balanceBlock.setAlignment(Pos.CENTER);


        // Buttons
        // Clear button

        Button clear = new Button();
        clear.setText("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nameInput.clear();
                ageInput.clear();
                emailInput.clear();
                balanceInput.clear();
            }
        });

        // SubmissionButton

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomerController customerController = new CustomerController();
                String newName = nameInput.getText();
                int newAge = Integer.parseInt(ageInput.getText());
                String newEmail = emailInput.getText();
                double newBal = Double.parseDouble(balanceInput.getText());

                customerController.addCustomer(newName,newEmail,newAge);
            }
        });
        // Button hbox
        HBox buttons = new HBox(clear,submit);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);

        // put all into this vbox

        VBox vertical = new VBox(titleBar,nameBlock,ageBlock,emailBlock,balanceBlock,buttons);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);

        root.setCenter(vertical);
        return register;
    }

    @Override
    public void start(Stage stage) throws Exception {
        String title = "DS Computing";
        stage.setTitle(title);
        BorderPane root = new BorderPane();
        Scene register = registerPage(root);
        stage.setScene(register);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}