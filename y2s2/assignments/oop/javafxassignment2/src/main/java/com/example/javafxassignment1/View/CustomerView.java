package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.javafxassignment1.View.MainView;

import static com.example.javafxassignment1.View.MainView.backBtn;

public class CustomerView {
    CustomerController controller;
    public CustomerView(CustomerController controller_){
        this.controller = controller_;
    }

    public VBox customerHome(Stage stage) {
        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Management");
        stage.setTitle(title.getText());

        // Customer Register button
        Button register = new Button();
        register.setText("Register");
        register.setOnAction(_ -> stage.getScene().setRoot(registerPage(stage)));

        // View Customers button
        Button view = new Button();
        view.setText("View other customers");
        view.setOnAction(_ -> stage.getScene().setRoot(viewCustomers(stage)));

        // Remove Customers button
        Button remove = new Button();
        remove.setText("Remove Customers");
        remove.setOnAction(_ -> stage.getScene().setRoot(removePage(stage)));

        // Load data button
        Button load = new Button();
        load.setText("Load Customer Data");
        load.setOnAction(_ -> stage.getScene().setRoot(loadData(stage)));

        // Save data button
        Button save = new Button();
        save.setText("Save Customer Data");
        save.setOnAction(_ -> controller.save());
        // Positioning the Components

        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        VBox vertical = new VBox(titleBar, register, view, remove, load,save);
        vertical.setSpacing(25);
        vertical.setAlignment(Pos.CENTER);

        return vertical;
    }

    public VBox registerPage(Stage stage) {

        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Register an account");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        stage.setTitle(title.getText());

        // Name Hbox label and input
        Label nameLabel = new Label("Name:");
        TextField nameInput = new TextField();
        HBox nameBlock = new HBox(nameLabel, nameInput);
        nameBlock.setAlignment(Pos.CENTER);

        // Age hbox label and input

        Label ageLabel = new Label("Age:");
        TextField ageInput = new TextField();
        HBox ageBlock = new HBox(ageLabel, ageInput);
        ageBlock.setAlignment(Pos.CENTER);

        // Email hbox label and input

        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();
        HBox emailBlock = new HBox(emailLabel, emailInput);
        emailBlock.setAlignment(Pos.CENTER);

        // Balance hbox label and input

        Label balanceLabel = new Label("Balance:");
        TextField balanceInput = new TextField();
        HBox balanceBlock = new HBox(balanceLabel, balanceInput);
        balanceBlock.setAlignment(Pos.CENTER);

        // Buttons

        // Clear button

        Button clear = new Button();
        clear.setText("Clear");
        clear.setOnAction(_ -> {
            nameInput.clear();
            ageInput.clear();
            emailInput.clear();
            balanceInput.clear();
        });

        // SubmissionButton

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(_ -> {
            controller.add(nameInput.getText(), emailInput.getText(), ageInput.getText(), balanceInput.getText());
            nameInput.clear();
            ageInput.clear();
            emailInput.clear();
            balanceInput.clear();
        });
        // Back button
        Button backbtn = backBtn(stage, customerHome(stage));
        // Button hbox
        HBox buttons = new HBox(clear, submit, backbtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);

        // put all into this vbox

        VBox vertical = new VBox(titleBar, nameBlock, ageBlock, emailBlock, balanceBlock, buttons);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);

        return vertical;
    }

    public VBox viewCustomers(Stage stage) {

        /*
         * Although the specs require the data to be stored in a text area, I find the presentation
         * of the data to be a lot clearer in the table format, I understand if I lose marks but this is
         * the best way to achieve viewing in my opinion
         * */

        Label title = new Label();
        title.setText("View All customers");
        stage.setTitle(title.getText());
        HBox titleBar = new HBox(title);

        // Table for Viewing

        TableView table = new TableView();
        table.setEditable(false);
        TableColumn<Customer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Customer, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Customer, Double> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        table.getColumns().addAll(idColumn, nameColumn, ageColumn, emailColumn,balanceColumn );
        // Buttons to view attributes

        Button backbtn = backBtn(stage, customerHome(stage));
        // Listing button
        Button list = new Button();

        list.setText("View Customers");
        list.setOnAction(_ -> {
            table.getItems().clear(); // clearing previous data
            table.getItems().addAll(controller.getStorage());

        });

        // Search button

        Button search = new Button();
        search.setText("Search");

        Label idSearch = new Label();
        idSearch.setText("Enter an id to search:");


        TextField idSearchInput = new TextField();
        HBox searchBlock = new HBox(idSearch, idSearchInput, search);
        // search.setDisable(MainView.inputAllower(idSearchInput)); // TODO fix input
        search.setDisable(false); // TODO fix input
        searchBlock.setAlignment(Pos.CENTER);
        search.setOnAction(_ -> {
            table.getItems().clear(); // clearing previous data
            for (Customer customer : controller.getStorage()) {
                if (customer.getId() == Integer.parseInt(idSearchInput.getText())) {
                    table.getItems().add(customer);
                }
            }

        });

        HBox actionButtons = new HBox(list, searchBlock);
        VBox main = new VBox(titleBar, actionButtons, table, backbtn);
        main.setAlignment(Pos.CENTER);
        main.setSpacing(30.0);

        return main;
    }

    public VBox removePage(Stage stage) {
        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Remove a customer");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);

        // ID hbox label and input

        Label idLabel = new Label("Id:");
        TextField idInput = new TextField();
        HBox idBlock = new HBox(idLabel, idInput);
        idBlock.setAlignment(Pos.CENTER);

        // Removal Submit button

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(_ -> {
            int removeId = Integer.parseInt(idInput.getText());
            controller.delete(removeId);

        });
        // Back button
        Button backbtn = backBtn(stage, customerHome(stage));

        HBox buttonSubmit = new HBox(submit, backbtn);

        buttonSubmit.setAlignment(Pos.CENTER);
        buttonSubmit.setSpacing(25.0);

        // put all into this vbox
        VBox vertical = new VBox(titleBar, idBlock, buttonSubmit);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);


        return vertical;
    }

    public VBox loadData(Stage stage) {
        // Header
        Label title = new Label();

        title.setText("DS Computers: Load Customers");
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);

        // Button to open file explorer

        // set title for the stage
        stage.setTitle("Load Customers!");
        Label warning = new Label();
        warning.setText("Warning, loading will delete all newly added customers!!");
        // Standard file loading

        // load Button
        Button loadData = new Button();
        loadData.setText("Load Saved Store");
        loadData.setOnAction(_ -> controller.load());

        Button backbtn = backBtn(stage, customerHome(stage));

        // create a VBox
        VBox vbox = new VBox(30, titleBox,warning ,loadData,backbtn);
        // set Alignment
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        // create a scene

        return vbox;
    }
}
