package com.example.javafxassignment1;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Models.Customer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class MainApplication extends Application {

  public Scene homePage(Stage stage) {
    BorderPane root = new BorderPane();

    // Init the scene

    Scene home = new Scene(root, 1000, 500);
    String cssSource = "com/example/javafxassignment1/styles.css";
    home.getStylesheets().add(MainApplication.requireNonNull(getClass().getResource(cssSource)).toExternalForm());
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

    });

    // Positioning the Components

    HBox titleBar = new HBox(title);
    titleBar.setAlignment(Pos.CENTER);
    VBox vertical = new VBox(titleBar, register, view, remove, load);
    vertical.setSpacing(25);
    vertical.setAlignment(Pos.CENTER);

    root.setCenter(vertical);

    return home;
  }

  public Scene registerPage(Stage stage) {
    BorderPane root = new BorderPane();
    // Init the scene

    Scene register = new Scene(root, 1000, 500);

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
      CustomerController customerController = new CustomerController();
      String newName = nameInput.getText();
      int newAge = Integer.parseInt(ageInput.getText());
      String newEmail = emailInput.getText();
      double newBal = Double.parseDouble(balanceInput.getText());

      customerController.addCustomer(newName, newEmail, newAge,newBal);
    });
    // Back button
    Button backbtn = backBtn(stage, homePage(stage));
    // Button hbox
    HBox buttons = new HBox(clear, submit, backbtn);
    buttons.setAlignment(Pos.CENTER);
    buttons.setSpacing(25.0);

    // put all into this vbox

    VBox vertical = new VBox(titleBar, nameBlock, ageBlock, emailBlock, balanceBlock, buttons);
    vertical.setAlignment(Pos.CENTER);
    vertical.setSpacing(30.0);

    root.setCenter(vertical);
    return register;
  }

  public Scene viewCustomers(Stage stage) {
    BorderPane root = new BorderPane();
    Scene view = new Scene(root,1000,500);
    CustomerController c = new CustomerController();

    // Heading

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
    idColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    TableColumn<Customer, Integer> ageColumn = new TableColumn<>("Age");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    TableColumn<Customer, Double> balanceColumn = new TableColumn<>("Balance");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));


    // Buttons to view attributes

    Button backbtn = backBtn(stage, homePage(stage));

    // Listing button
    Button list = new Button();
    list.setText("View Customers");
    list.setOnAction(_ -> {
      for (Customer customer : c.getCustomers()) {
        if (customer.getId() == 0) {
          continue;
        } else {
          table.getItems().add(customer);
        }
      }

      table.getColumns().addAll(idColumn, nameColumn, ageColumn, balanceColumn, emailColumn);
    });

    // Search Button : Coming soon: With SQLite
    Button search = new Button();
    search.setText("Search");
    Label idSearch = new Label();
    idSearch.setText("Enter an id to search:");
    TextField idSearchInput = new TextField();
    HBox searchBlock = new HBox(idSearch, idSearchInput,search);
    searchBlock.setAlignment(Pos.CENTER);
    search.setOnAction(_ -> {
      for (Customer customer : c.getCustomers()) {
        if (customer.getId() == Integer.parseInt(idSearchInput.getText())) {
          table.getItems().add(customer);
        } else {
        }
      }

      table.getColumns().addAll(idColumn, nameColumn, ageColumn, balanceColumn, emailColumn);
    });


    HBox actionButtons = new HBox(list,searchBlock);
    VBox main = new VBox(titleBar,actionButtons,table,backbtn);
    root.setCenter(main);
    return view;
  }

  public Scene removePage(Stage stage) {
    BorderPane root = new BorderPane();
    Scene remove = new Scene(root, 1000, 500);

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
      CustomerController customerController = new CustomerController();
      int removeId = Integer.parseInt(idInput.getText());
      customerController.deleteCustomer(removeId);

    });
    // Back button
    Button backbtn = backBtn(stage, homePage(stage));

    HBox buttonSubmit = new HBox(submit, backbtn);

    buttonSubmit.setAlignment(Pos.CENTER);
    buttonSubmit.setSpacing(25.0);

    // put all into this vbox
    VBox vertical = new VBox(titleBar, idBlock, buttonSubmit);
    vertical.setAlignment(Pos.CENTER);
    vertical.setSpacing(30.0);

    root.setCenter(vertical);

    return remove;
  }

  public Scene loadData(Stage stage) {
    BorderPane root = new BorderPane();
    Scene load = new Scene(root, 1000, 500);

    // Header
    Label title = new Label();

    title.setText("DS Computers: Load Customers");
    HBox titleBox = new HBox(title);
    titleBox.setAlignment(Pos.CENTER);

    // Button to open file explorer
    try {

      // set title for the stage
      stage.setTitle("Load Customers!");

      // create a File chooser
      FileChooser file_chooser = new FileChooser();

      // create a Label
      Label label = new Label("no files selected");

      // create a Button
      Button button = new Button("Select customers file");

      EventHandler<ActionEvent> event = _ -> {

          File file = file_chooser.showOpenDialog(stage);

          if (file != null) {
              label.setText(file.getAbsolutePath());
          }
      };
      button.setOnAction(event);
      // Submission Button
      Button submitData = new Button();
      submitData.setText("Submit data");
      String finalFilePath = label.getText();
      submitData.setOnAction(_ -> {
        CustomerController customerController = new CustomerController();
        customerController.loadCustomers(finalFilePath);

      });

      Button backbtn = backBtn(stage, homePage(stage));

      // create a VBox
      VBox vbox = new VBox(30, titleBox, label, button, submitData, backbtn);

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

  // Custom buttons

  // Back button

  public Button backBtn(Stage stage, Scene previous) {
    Button backBtn = new Button();
    backBtn.setText("Back");

    backBtn.setOnAction(_ -> stage.setScene(previous));
    return backBtn;
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
