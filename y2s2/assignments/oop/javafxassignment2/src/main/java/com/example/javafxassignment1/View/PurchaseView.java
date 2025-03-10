package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Controllers.ProductController;
import com.example.javafxassignment1.Controllers.PurchaseController;
import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PurchaseView {

    PurchaseController controller = new PurchaseController();
    CustomerController customerController = new CustomerController();
    ProductController productController = new ProductController();

    public PurchaseView(
        PurchaseController controller_,
        CustomerController cscntrl,
        ProductController pdcntrl
    ) {
        this.controller = controller_;
        this.customerController = cscntrl;
        this.productController = pdcntrl;
    }

    public BorderPane mainPurchase(Stage stage) {
        // Header Bar
        Customer currentCustomer;

        Label title = new Label();
        title.setText("DS Computing: Buy a computer");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        stage.setTitle(title.getText());

        // Combo boxes
        ArrayList<Customer> customers = customerController.getStorage();
        ComboBox<Customer> customersBox = new ComboBox<Customer>();
        customersBox.getItems().addAll(customers);

        ArrayList<Product> products = productController.getStorage();
        ComboBox<Product> productBox = new ComboBox<Product>();
        productBox.getItems().addAll(products);
        HBox dropDowns = new HBox(customersBox, productBox);

        // Buttons

        Button selectCustomer = new Button();
        selectCustomer.setText("Select customer");
        selectCustomer.setOnAction(_->{
            currentCustomer = customersBox.getValue();
        });


        Button addProduct = new Button();
        addProduct.setText("Select product to add");
        addProduct.setOnAction(_->{
            purchaseController.addToCart(productBox.getValue());
        });

        Button checkout = new Button();
        checkout.setText("Checkout");
        checkout.setOnAction(_->{
            controller.makePurchase(currentCustomer);
        });


        HBox buttons = new HBox(selectCustomer,addProduct);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);
        // Displaying info

        Label totalDescriptor = new Label("Total:");
        Label totalAmount = new Label();
        double totalCalc = 2.0; //TODO  calculate total
        totalAmount.setText("€"+totalCalc);

        HBox totals = new HBox(totalDescriptor,totalAmount);
        totals.setSpacing(25.0);

        // put all into this vbox

        VBox vertical = new VBox(titleBar, dropDowns, buttons,totals);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);

        BorderPane root = new BorderPane(vertical);
        root.setCenter(vertical);
        return root;
    }
}
