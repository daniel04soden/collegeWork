package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Scene mainMenu(Stage stage){
            // Tab Pane setup

            TabPane tabPane = new TabPane();

            // Disabling tab closing as we need these tabs

            TabPane.TabClosingPolicy unavailable = TabPane.TabClosingPolicy.UNAVAILABLE;
            tabPane.setTabClosingPolicy(unavailable);

            // Naming and init tabs
            List<String> nameOfTabs = Arrays.asList("Customers","Ordering","Management");
            for (String tabName : nameOfTabs) {
                Tab tab = new Tab(tabName);
                //TODO tab.setContent(new StackPane(futureStackPane?)); - Switching needs work too
                tabPane.getTabs().add(tab);
            }
            BorderPane root = new BorderPane(tabPane);
            // Init the scene

            Scene home = new Scene(root, 1000, 500);
            applyCSS(home);
            // Header Bar
            Label title = new Label();
            title.setText("DS Computing: Management");
            stage.setTitle(title.getText());

            // Customer Register button
            Button register = new Button();
            register.setText("Register");
            register.setOnAction(_ -> stage.setScene(registerPage(stage)));

            // View Customers button
            Button view = new Button();
            view.setText("View other customers");
            view.setOnAction(_ -> stage.setScene(viewCustomers(stage)));

            // Remove Customers button

            Button remove = new Button();
            remove.setText("Remove Customers");
            remove.setOnAction(_ -> stage.setScene(removePage(stage)));

            // Load data button
            Button load = new Button();
            load.setText("Load Customer Data");
            load.setOnAction(_ -> stage.setScene(loadData(stage)));
            // Save data button
            Button save = new Button();
            save.setText("Save Customer Data");
            save.setOnAction(_ -> {
                CustomerController.saveCustomers(customers);
                CustomerController.printCustomers(customers);
            });
            // Positioning the Components

            HBox titleBar = new HBox(title);
            titleBar.setAlignment(Pos.CENTER);
            VBox vertical = new VBox(titleBar, register, view, remove, load,save);
            vertical.setSpacing(25);
            vertical.setAlignment(Pos.CENTER);

            root.setCenter(vertical);
            root.setLeft(tabPane);

            return home;
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
