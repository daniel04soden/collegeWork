package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.PurchaseController;
import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.example.javafxassignment1.View.MainView.backBtn;

public class PurchaseView {
    private PurchaseController prc;
    public TextArea area;
    public PurchaseView(PurchaseController prc_){
       this.prc = prc_;
    }
    public VBox mainPurchase(Stage stage) {
        // Header Bar

        Label title = new Label();
        title.setText("DS Computing: Buy a computer");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        stage.setTitle(title.getText());

        // Combo boxes
        ArrayList<Customer> customers = prc.mc.cc.getStorage(); // Persistent storage rather than reinit storage
        ComboBox<Customer> customersBox = new ComboBox<>();
        customersBox.getItems().addAll(customers);
        customersBox.setPromptText("Who are you?");

        ArrayList<Product> products = prc.mc.pc.getStorage();
        ArrayList<Product> runningCart = prc.getRunningCart();
        ComboBox<Product> productBox = new ComboBox<>();
        productBox.getItems().addAll(products);
        productBox.setPromptText("Pick a PC");
        HBox dropDowns = new HBox(customersBox, productBox);
        dropDowns.setAlignment(Pos.CENTER);
        // Buttons

        Button addProduct = new Button();
        TextArea area = new TextArea();
        area.setEditable(false);
        addProduct.setText("Select product to add");
        addProduct.setOnAction(_->{
            runningCart.add(productBox.getValue());
            area.clear();
            prc.displayCartInfo(area);
        });
        HBox cartInfo = new HBox(area);
        cartInfo.setAlignment(Pos.CENTER);

        Button checkout = new Button();
        Label outcome = new Label();
        Label total = new Label();
        checkout.setText("Checkout");
        checkout.setOnAction(_->{
            Purchase purchase = new Purchase(customersBox.getValue(),runningCart);
            total.setText("€"+String.valueOf(purchase.calcTotal()));
            boolean confirmation = purchase.confirmPurchase();
            if (!confirmation){
               outcome.setText("Payment cannot go through so sad");
               System.out.println("their balance: €" + purchase.getBuyer().getBalance());
            }else{
                outcome.setText("Purchase confirmed " + purchase.toString());
                prc.mc.pc.save();
                prc.mc.cc.save();
                System.out.println(purchase.toString());
            }
        });

        HBox buttons = new HBox(addProduct,checkout);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);
        // Displaying info

        Label totalDescriptor = new Label("Total:");

        HBox totals = new HBox(totalDescriptor,total,outcome);
        totals.setId("totalsColor");
        totals.setAlignment(Pos.CENTER);
        totals.setSpacing(25.0);

        // put all into this vbox

        VBox vertical = new VBox(titleBar, dropDowns,cartInfo, buttons,totals);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);

        return vertical;
    }

    public VBox purchasingHistory(Stage stage){
            Label title = new Label();
            title.setText("View previous Customer Purchases");
            stage.setTitle(title.getText());
            HBox titleBar = new HBox(title);

            // Table for Viewing

            TableView table = new TableView();
            table.setEditable(false);
            TableColumn<Purchase, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            table
                    .getColumns()
                    .addAll(
                            idColumn
                    );
            // Buttons to view attributes

            Button backbtn = backBtn(stage, mainPurchase(stage));
            // Listing button
            Button list = new Button();

            list.setText("View Products");
            list.setOnAction(_ -> {
                table.getItems().clear(); // clearing previous data
                // table.getItems().addAll(.getStorage()); //TODO add proper purchasing storage
            });

            // Search button

            Button find = new Button();
            find.setText("Find");

            Label idSearch = new Label();
            idSearch.setText("Enter product id to search:");

            TextField idSearchInput = new TextField();

            HBox searchBlock = new HBox(idSearch, idSearchInput, find);
            searchBlock.setAlignment(Pos.CENTER);

                /*
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
            */

            HBox actionButtons = new HBox(list, searchBlock);
            VBox vertical = new VBox(titleBar, actionButtons, table, backbtn);
            vertical.setAlignment(Pos.CENTER);
            vertical.setSpacing(30.0);
            return vertical;
        }
    }
}
