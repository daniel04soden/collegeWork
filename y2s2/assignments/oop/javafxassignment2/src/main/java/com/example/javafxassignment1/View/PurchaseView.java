package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.PurchaseController;
import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
import com.example.javafxassignment1.View.MainView;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;


public class PurchaseView {
    private PurchaseController prc;
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
        Button history = new Button("Previous order's history");
        history.setOnAction(_->{
            stage.getScene().setRoot(purchasingHistory(stage));
        });
        HBox topRow = new HBox(customersBox, productBox,history);
        topRow.setAlignment(Pos.CENTER);
        topRow.setSpacing(30);
        // Buttons

        Button addProduct = new Button();
        TextArea area = new TextArea();
        area.setEditable(false);
        addProduct.setText("Select product to add");
        addProduct.setOnAction(_->{
            runningCart.add(productBox.getValue());
            area.clear();
            prc.displayCartInfo(area);
            productBox.setValue(null);
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

        VBox vertical = new VBox(titleBar, topRow,cartInfo, buttons,totals);
        vertical.setSpacing(30.0);

        return vertical;
    }

    public VBox purchasingHistory(Stage stage){
        Label title = new Label();
        title.setText("View previous Customer Purchases");
        stage.setTitle(title.getText());
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        Button showAllOrder = new Button("All Orders");
        Button searchOrder = new Button("Search for order");
        Label orderInput = new Label();
        orderInput.setText("Enter an order Id to search:");
        TextField idSearchInput = new TextField();

        HBox actionButtons = new HBox(showAllOrder,searchOrder,orderInput,idSearchInput);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setSpacing(15);

        TextArea orderInfo = new TextArea();
        orderInfo.setEditable(false);
        showAllOrder.setOnAction(_->{
           orderInfo.clear();
            for (Purchase purchases: prc.getPurchases()){
                orderInfo.appendText("\n"+purchases.toString());
            }

        });
        HBox orderBox = new HBox(orderInfo);
        orderBox.setAlignment(Pos.CENTER);

        Button sortOrders = new Button("Sort by date");
        Button back = new Button("Back");

        back.setOnAction(_->{
            stage.getScene().setRoot(mainPurchase(stage));
        });
        HBox bottomButtons = new HBox(sortOrders,back);
        MainView.styleHbox(bottomButtons,20);
        VBox vertical = new VBox(titleBar, actionButtons,orderBox,bottomButtons);
        vertical.setSpacing(30.0);
        return vertical;
    }
}
