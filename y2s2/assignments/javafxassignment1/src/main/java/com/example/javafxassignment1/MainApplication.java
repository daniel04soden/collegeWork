package com.example.javafxassignment1;

import com.example.javafxassignment1.Controllers.CustomerController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainApplication extends Application {

    public Scene homePage(Stage stage){
        BorderPane root = new BorderPane();
        // Init the scene

        Scene home = new Scene(root,1000,500);

        // Header Bar
        Label title= new Label();
        title.setText("DS Computing: Management");

        // Customer Register button
        Button register = new Button();
        register.setText("Register");
        register.setOnAction(actionEvent -> stage.setScene(registerPage(stage)));
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

    public Scene registerPage(Stage stage){
        BorderPane root = new BorderPane();
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
        clear.setOnAction(actionEvent -> {
            nameInput.clear();
            ageInput.clear();
            emailInput.clear();
            balanceInput.clear();
        });

        // SubmissionButton

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(actionEvent -> {
            CustomerController customerController = new CustomerController();
            String newName = nameInput.getText();
            int newAge = Integer.parseInt(ageInput.getText());
            String newEmail = emailInput.getText();
            double newBal = Double.parseDouble(balanceInput.getText());

            customerController.addCustomer(newName,newEmail,newAge);
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

    //public Scene listPage(BorderPane root){

    //}

    public Scene removePage(BorderPane root,Stage stage){

        Scene remove = new Scene(root,1000,500);

        // Header Bar
        Label title= new Label();
        title.setText("DS Computing: Remove a customer");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);

        // Id hbox label and input

        Label idLabel = new Label("Id:");
        TextField idInput = new TextField();
        HBox idBlock = new HBox(idLabel,idInput);
        idBlock.setAlignment(Pos.CENTER);

        // Removal Submit button

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(actionEvent -> {
            CustomerController customerController = new CustomerController();
            int removeId = Integer.parseInt(idInput.getText());
            customerController.deleteCustomer(removeId);

        });

        HBox buttonSubmit = new HBox(submit);
        buttonSubmit.setAlignment(Pos.CENTER);
        buttonSubmit.setSpacing(25.0);


        // put all into this vbox
        VBox vertical = new VBox(titleBar,idBlock,buttonSubmit);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);

        root.setCenter(vertical);

        return remove;
    }

     public Scene loadData(BorderPane root,Stage stage){
        Scene load = new Scene(root,1000,500);

        // Header
        Label title = new Label();

        title.setText("DS Computers: Load Customers");
        HBox titleBox = new HBox(title);

        // Button to open file explorer
         try {

             // set title for the stage
             stage.setTitle("Load Customers!");

             // create a File chooser
             FileChooser fil_chooser = new FileChooser();

             // create a Label
             Label label = new Label("no files selected");

             // create a Button
             Button button = new Button("Show open dialog");

             // create an Event Handler
             EventHandler<ActionEvent> event =
                     new EventHandler<ActionEvent>() {

                         public void handle(ActionEvent e)
                         {

                             // get the file selected
                             File file = fil_chooser.showOpenDialog(stage);

                             if (file != null) {

                                 label.setText(file.getAbsolutePath()
                                         + "  selected");
                             }
                         }
                     };

             button.setOnAction(event);

             // create a VBox
             VBox vbox = new VBox(30, label, button);

             // set Alignment
             vbox.setAlignment(Pos.CENTER);
             root.setCenter(vbox);
             // create a scene
         }

         catch (Exception e) {

             System.out.println(e.getMessage());
         }
        return load;
    }
    @Override
    public void start(Stage stage) throws Exception {
        String title = "DS Computing Customer Management Portal";
        stage.setTitle(title);
        Scene home = homePage(stage);
        stage.setScene(home);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}