package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class CustomerView {
    CustomerController controller;
    public CustomerView(CustomerController controller_){
        this.controller = controller_;
    }
    public SplitPane splitInit(Stage stage) {
        SplitPane splitPane = new SplitPane();

        VBox sidebar = createSidebar(stage, splitPane);
        VBox content = registerPage(stage);

        splitPane.getItems().addAll(sidebar, content);
        splitPane.setDividerPositions(0.2);

        return splitPane;
    }

    private VBox createSidebar(Stage stage, SplitPane splitPane) {
        Button register = new Button("Register");
        register.setOnAction(_ -> MainView.updateContent(splitPane, registerPage(stage)));

        Button view = new Button("View other customers");
        view.setOnAction(_ -> MainView.updateContent(splitPane, viewCustomers(stage)));

        Button removePage = new Button();
        removePage.setText("Remove Customers");
        removePage.setOnAction(_ -> MainView.updateContent(splitPane, removePage(stage)));

        Button balanceIncr = new Button();
        balanceIncr.setText("Increase customer balance");
        balanceIncr.setOnAction(_ -> MainView.updateContent(splitPane, addToBalance(stage)));

        Button management = new Button();
        management.setText("Manage Customer Data");
        management.setOnAction(_ -> MainView.updateContent(splitPane, loadOrSaveData(stage)));

        Button editCustomerDbData = new Button();
        editCustomerDbData.setText("Edit Customers");
        editCustomerDbData.setOnAction(_ -> MainView.updateContent(splitPane, updatePageViewPage(stage)));


        VBox sidebar = new VBox(register, view,removePage,balanceIncr,management,editCustomerDbData);
        sidebar.setSpacing(15);
        sidebar.setAlignment(Pos.CENTER);

        return sidebar;
    }


    public VBox registerPage(Stage stage) {
        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Register an account");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        stage.setTitle(title.getText());
        // Error value field
        Label result = new Label("");
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
            MainView.validateText(nameInput,result);
            MainView.validateText(ageInput,result);
            MainView.validateText(emailInput,result);
            MainView.validateText(balanceInput,result);
            controller.add(nameInput.getText(), emailInput.getText(), ageInput.getText(), balanceInput.getText());
            nameInput.clear();
            ageInput.clear();
            emailInput.clear();
            balanceInput.clear();
        });
        // Back button
        // Button hbox
        HBox buttons = new HBox(clear, submit);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);

        // put all into this vbox

        VBox vertical = new VBox(titleBar, nameBlock, ageBlock, emailBlock, balanceBlock, buttons,result);
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
        VBox main = new VBox(titleBar, actionButtons, table);
        main.setAlignment(Pos.CENTER);
        main.setSpacing(30.0);

        return main;
    }

    public VBox removePage(Stage stage) {
        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Remove a customer");
        stage.setTitle(title.getText());
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);

        // ID hbox label and input

        Label customerDisplay = new Label("Customer:");
        ComboBox<Customer> customerComboBox = new ComboBox<>();
        customerComboBox.getItems().addAll(controller.getStorage());
        HBox idBlock = new HBox(customerDisplay, customerComboBox);
        idBlock.setAlignment(Pos.CENTER);

        // Removal Submit button

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(_ -> {
            int removeId = customerComboBox.getValue().getId();
            controller.delete(removeId);
            customerComboBox.setValue(null); // clear so no repeat click
        });
        // Back button

        HBox buttonSubmit = new HBox(submit);

        buttonSubmit.setAlignment(Pos.CENTER);
        buttonSubmit.setSpacing(25.0);

        // put all into this vbox
        VBox vertical = new VBox(titleBar, idBlock, buttonSubmit);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);


        return vertical;
    }

    public VBox addToBalance(Stage stage) {
        Label title = new Label("Adding to a customer's balance");
        stage.setTitle(title.getText());
        HBox titleBar = new HBox(title);
        MainView.styleHbox(titleBar, 10);

        ArrayList<Customer> customers = controller.getStorage();
        ComboBox<Customer> customerComboBox = new ComboBox<>();
        customerComboBox.getItems().addAll(customers);
        customerComboBox.setPromptText("Add balance to which account:");
        Button confirmAmount = new Button("Confirm amount");
        TextField amountToAdd = new TextField();
        confirmAmount.setOnAction(_-> controller.balanceAdj(amountToAdd,customerComboBox));
        HBox combo = new HBox(customerComboBox,amountToAdd);
        MainView.styleHbox(combo, 10);
        HBox buttons = new HBox(confirmAmount);
        MainView.styleHbox(buttons, 10);
        VBox vertical = new VBox(titleBar,combo,buttons);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);
        return vertical;
    }


    public VBox loadOrSaveData(Stage stage) {
        // Header
        Label title = new Label();

        title.setText("DS Computers: Manage Customer Data");
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
        loadData.setOnAction(_ -> {
            controller.loadCustomersFromDb();
            MainView.displayLoad(); 
        });

        Button saveButton = new Button();
        saveButton.setText("Save Customer Data");
        saveButton.setOnAction(_ -> {
            controller.save();
            MainView.displaySave(); 
        });

        // create a VBox
        VBox vbox = new VBox(30, titleBox,warning ,loadData,saveButton);
        // set Alignment
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        // create a scene

        return vbox;
    }

    public VBox updatePageViewPage(Stage stage) {
        Label title = new Label("SQL Data Interaction");
        stage.setTitle(title.getText());
        HBox titleBar = new HBox(title);
        ArrayList<Customer> customers = controller.getStorage();
        ComboBox<Customer> customerComboBox = new ComboBox<>();
        customerComboBox.getItems().addAll(customers);
        customerComboBox.setPromptText("Which Customer do you want to edit");

        HBox checkOnCustomer = viewDbData(customerComboBox);


        Label nameLabel = new Label("New Name:");
        TextField nameInput = new TextField();
        HBox nameBlock = new HBox(nameLabel, nameInput);
        nameBlock.setAlignment(Pos.CENTER);

        // Age hbox label and input

        Label ageLabel = new Label("New Age:");
        TextField ageInput = new TextField();
        HBox ageBlock = new HBox(ageLabel, ageInput);
        ageBlock.setAlignment(Pos.CENTER);
        // Email hbox label and input

        Label emailLabel = new Label("New Email:");
        TextField emailInput = new TextField();
        HBox emailBlock = new HBox(emailLabel, emailInput);
        emailBlock.setAlignment(Pos.CENTER);

        // Balance hbox label and input

        Label balanceLabel = new Label("New Balance:");
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
            controller.editCustomer(
                    customerComboBox,
                    Integer.parseInt(ageInput.getText()),
                    nameInput.getText(),
                    emailInput.getText(),
                    Double.parseDouble(balanceInput.getText())
            );
            nameInput.clear();
            ageInput.clear();
            emailInput.clear();
            balanceInput.clear();
        });
        HBox buttonBlock = new HBox(submit,clear);


        return new VBox(
                titleBar,customerComboBox,checkOnCustomer,nameBlock,ageBlock,balanceBlock,emailBlock,buttonBlock
        );
    }

    private HBox viewDbData(ComboBox<Customer> cb){
        Button currentData = new Button("Check Selected Customer data");
        TextArea display = new TextArea();
        display.setEditable(false);

        currentData.setOnAction(_->{
            int id = cb.getValue().getId();
            display.clear();
            display.appendText(controller.getDbCustomer(id));
        });
        return new HBox(currentData,display);
    }
}
