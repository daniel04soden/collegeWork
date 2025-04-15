package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.ProductController;
import com.example.javafxassignment1.Models.Product;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ProductView {

    ProductController controller;

    public ProductView(ProductController controller_) {
        this.controller = controller_;
    }

    public SplitPane splitInit(Stage stage) {
        SplitPane splitPane = new SplitPane();

        VBox sidebar = createSidebar(stage, splitPane);
        VBox content = productRegisterPage(stage);

        splitPane.getItems().addAll(sidebar, content);
        splitPane.setDividerPositions(0.2);

        return splitPane;
    }

    private VBox createSidebar(Stage stage, SplitPane splitPane) {
        Button register = new Button("Register");
        register.setOnAction(_ -> MainView.updateContent(splitPane, productRegisterPage(stage)));

        Button view = new Button("View Products");
        view.setOnAction(_ -> MainView.updateContent(splitPane, viewProducts(stage)));

        Button removePageButton = new Button();
        removePageButton.setText("Remove Product");
        removePageButton.setOnAction(_ -> MainView.updateContent(splitPane, removeProduct(stage)));

        Button load = new Button();
        load.setText("Manage Product Data");
        load.setOnAction(_ -> MainView.updateContent(splitPane, loadProducts(stage)));


        VBox sidebar = new VBox(register, view,removePageButton,load);
        sidebar.setSpacing(15);
        sidebar.setAlignment(Pos.CENTER);

        return sidebar;
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
        clear.setOnAction(_ -> clearInputFields.run());

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
        // Button hbox
        HBox buttons = new HBox(clear, submit);
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
                }
            }
        });

        HBox actionButtons = new HBox(list, searchBlock);
        VBox vertical = new VBox(titleBar, actionButtons, table);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);
        return vertical;
    }

    public VBox removeProduct(Stage stage) {
        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Remove a product");
        stage.setTitle(title.getText());
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

        HBox buttonSubmit = new HBox(submit);

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
            MainView.displayLoad(true); //TODO - make boolean check in controller
        });

        Button saveButton = new Button();
        saveButton.setText("Save Product Data");
        saveButton.setOnAction(_ -> {
            controller.save();
            MainView.displaySave(true); //TODO - make boolean check in controller
        });

        // create a VBox
        VBox vbox = new VBox(30, titleBox, warning, loadData,saveButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30.0);


        // set Alignment
        return vbox;
    }


}
