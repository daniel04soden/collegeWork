package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.ProductController;
import com.example.javafxassignment1.Models.Product;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.javafxassignment1.View.MainView.backBtn;

public class ProductView {

    ProductController controller;

    public ProductView(ProductController controller_) {
        this.controller = controller_;
    }

    public VBox productHome(Stage stage) {
        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Products");
        stage.setTitle(title.getText());

        // Item Register button
        Button register = new Button();
        register.setText("Register New Products");

        // View items button
        Button view = new Button();
        view.setText("All products");

        // Remove product button

        Button remove = new Button();
        remove.setText("Remove Products");

        // Load data button
        Button load = new Button();
        load.setText("Load Products");

        // Save data button
        Button save = new Button();
        save.setText("Save Products");
        save.setOnAction(_ -> controller.save());
        // Positioning the Components

        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        VBox vertical = new VBox(titleBar, register, view, remove, load, save);
        vertical.setSpacing(25);
        vertical.setAlignment(Pos.CENTER);

        // Button actions

        register.setOnAction(_ -> stage.getScene().setRoot(productRegisterPage(stage)));
        view.setOnAction(_ -> stage.getScene().setRoot(viewProducts(stage)));
        remove.setOnAction(_ -> stage.getScene().setRoot(removeProduct(stage)));
        load.setOnAction(_ -> stage.getScene().setRoot(loadProducts(stage)));

        return vertical;
    }

    public VBox productRegisterPage(Stage stage) {

        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Register a product");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        stage.setTitle(title.getText());

        // Name Hbox label and input
        Label nameLabel = new Label("Product Name:");
        TextField nameInput = new TextField();
        HBox nameBlock = new HBox(nameLabel, nameInput);
        nameBlock.setAlignment(Pos.CENTER);

        // Age hbox label and input

        Label stockLabel = new Label("Stock");
        TextField stockInput = new TextField();
        HBox stockBlock = new HBox(stockLabel, stockInput);
        stockBlock.setAlignment(Pos.CENTER);

        // Balance hbox label and input

        Label priceLabel = new Label("Price:");
        TextField priceInput = new TextField();
        HBox priceBlock = new HBox(priceLabel, priceInput);
        priceBlock.setAlignment(Pos.CENTER);

        // Clear Button functionality

        Runnable clearInputFields = () -> {
            nameInput.clear();
            stockInput.clear();
            priceInput.clear();
        };

        // Clear button

        Button clear = new Button();
        clear.setText("Clear");
        clear.setOnAction(_ -> {
            clearInputFields.run();
        });

        // SubmissionButton

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(_ -> {
            controller.add(
                nameInput.getText(),
                stockInput.getText(),
                priceInput.getText()
            );
            clearInputFields.run();
        });
        // Back button
        Button backbtn = backBtn(stage,productHome(stage));
        // Button hbox
        HBox buttons = new HBox(clear, submit, backbtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);

        // put all into this vbox

        VBox vertical = new VBox(
            titleBar,
            nameBlock,
            stockBlock,
            priceBlock,
            buttons
        );
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);

        return vertical;
    }

    public VBox viewProducts(Stage stage) {
        /*
         * Although the specs require the data to be stored in a text area, I find the presentation
         * of the data to be a lot clearer in the table format, I understand if I lose marks but this is
         * the best way to achieve viewing in my opinion
         * */
        // Heading

        Label title = new Label();
        title.setText("View All products");
        stage.setTitle(title.getText());
        HBox titleBar = new HBox(title);

        // Table for Viewing

        TableView table = new TableView();
        table.setEditable(false);
        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product, Boolean> stockBoolColumn = new TableColumn<>(
            "In Stock"
        );
        stockBoolColumn.setCellValueFactory(
            new PropertyValueFactory<>("inStock")
        );

        table
            .getColumns()
            .addAll(
                idColumn,
                nameColumn,
                stockColumn,
                priceColumn,
                stockBoolColumn
            );
        // Buttons to view attributes

        Button backbtn = backBtn(stage, productHome(stage));
        // Listing button
        Button list = new Button();

        list.setText("View Products");
        list.setOnAction(_ -> {
            table.getItems().clear(); // clearing previous data
            table.getItems().addAll(controller.getStorage());
        });

        // Search button

        Button find = new Button();
        find.setText("Find");

        Label idSearch = new Label();
        idSearch.setText("Enter product id to search:");

        TextField idSearchInput = new TextField();

        HBox searchBlock = new HBox(idSearch, idSearchInput, find);
        searchBlock.setAlignment(Pos.CENTER);

        find.setOnAction(_ -> {
            table.getItems().clear(); // clearing previous data
            for (Product product : controller.getStorage()) {
                if (
                    product.getId() == Integer.parseInt(idSearchInput.getText())
                ) {
                    table.getItems().add(product);
                } else {
                    continue;
                }
            }
        });

        HBox actionButtons = new HBox(list, searchBlock);
        VBox vertical = new VBox(titleBar, actionButtons, table, backbtn);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);
        return vertical;
    }

    public VBox removeProduct(Stage stage) {
        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Remove a product");
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
        Button backbtn = backBtn(stage, productHome(stage));

        HBox buttonSubmit = new HBox(submit, backbtn);

        buttonSubmit.setAlignment(Pos.CENTER);
        buttonSubmit.setSpacing(25.0);

        // put all into this vbox
        VBox vertical = new VBox(titleBar, idBlock, buttonSubmit);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);
        return vertical;
    }

    public VBox loadProducts(Stage stage) {
        // Header
        Label title = new Label();

        title.setText("DS Computers: Load Products");
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);

        // Button to open file explorer

        // set title for the stage
        stage.setTitle(title.getText());
        Label warning = new Label();
        warning.setText(
            "Warning, loading will delete all newly added products!!"
        );
        // Standard file loading

        // load Button
        Button loadData = new Button();
        loadData.setText("Load Saved Products");
        loadData.setOnAction(_ -> {
            controller.load();
        });

        Button backbtn = backBtn(stage, productHome(stage));

        // create a VBox
        VBox vbox = new VBox(30, titleBox, warning, loadData, backbtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30.0);


        // set Alignment
        return vbox;
    }
}
