package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.MainController;
import com.example.javafxassignment1.Controllers.ProductController;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Store;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.javafxassignment1.Controllers.Threads.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


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
        load.setOnAction(_ -> MainView.updateContent(splitPane, loadSaveProducts(stage)));

        Button saveStore = new Button();
        saveStore.setText("Save and Load store state");
        saveStore.setOnAction(_->MainView.updateContent(splitPane,storeStateLoadSave(stage)));

        Hyperlink link = getHyperlink();

        VBox sidebar = new VBox(register, view,removePageButton,load,saveStore,link);
        sidebar.setSpacing(15);
        sidebar.setAlignment(Pos.CENTER);

        return sidebar;
    }

    private static Hyperlink getHyperlink() {
        Hyperlink link = new Hyperlink();
        String url = "https://www.tiktok.com/@satoruhm2/video/7457291318276934917?is_from_webapp=1&sender_device=pc&web_id=7488292820903233046";
        link.setText("Really important To the project");
        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(new URI(url));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Skill issue browser no worky");
                }
            }
        });
        return link;
    }

    public VBox storeStateLoadSave(Stage stage){
        // Header
        Label title = new Label();

        title.setText("Save store State");
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);

        stage.setTitle(title.getText());
        Label warning = new Label();
        warning.setText(
                "Warning, loading will delete entire store state"
        );

        loadStoreThread lT = new loadStoreThread(Store.getStore());
        saveStoreThread sT = new saveStoreThread(Store.getStore());

        // load Button
        Button loadStoreState = new Button();
        loadStoreState.setText("Load Store state");
        loadStoreState.setOnAction(_ -> {
            Thread loadStoreThread = new Thread(lT);
            loadStoreThread.start();
            MainView.displayLoad();
        });

        Button saveStoreStateButton = new Button();
        saveStoreStateButton .setText("Save store state");
        saveStoreStateButton.setOnAction(_ -> {
            Thread saveThread = new Thread(sT);
            saveThread.start();
            MainView.displaySave();
        });

        // create a VBox
        VBox vbox = new VBox(30, titleBox, warning, loadStoreState,saveStoreStateButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30.0);


        // set Alignment
        return vbox;
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

        table
            .getColumns()
            .addAll(
                idColumn,
                nameColumn,
                stockColumn,
                priceColumn
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

    public VBox loadSaveProducts(Stage stage) {
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
            controller.loadProductsFromDb();
            MainView.displayLoad();
        });

        Button saveButton = new Button();
        saveButton.setText("Save Product Data");
        saveButton.setOnAction(_ -> {
            controller.save();
            MainView.displaySave(); 
        });

        // create a VBox
        VBox vbox = new VBox(30, titleBox, warning, loadData,saveButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30.0);


        // set Alignment
        return vbox;
    }


}
