package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.PurchaseController;
import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;
import com.example.javafxassignment1.Models.Purchase;
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

    public SplitPane splitInit(Stage stage) {
        SplitPane splitPane = new SplitPane();

        VBox sidebar = createSidebar(stage, splitPane);
        VBox content = mainPurchase(stage);

        splitPane.getItems().addAll(sidebar, content);
        splitPane.setDividerPositions(0.2);

        return splitPane;
    }

    private VBox createSidebar(Stage stage, SplitPane splitPane) {
        Button main = new Button("Make a purchase");
        main.setOnAction(_ -> MainView.updateContent(splitPane, mainPurchase(stage)));

        Button history = new Button();
        history.setText("Check Order history");
        history.setOnAction(_ -> MainView.updateContent(splitPane, purchasingHistory(stage)));

        VBox sidebar = new VBox(main,history);
        sidebar.setSpacing(15);
        sidebar.setAlignment(Pos.CENTER);

        return sidebar;
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
        HBox topRow = new HBox(customersBox, productBox);
        topRow.setAlignment(Pos.CENTER);
        topRow.setSpacing(30);
        // Buttons

        Button addProduct = new Button();
        Label total = new Label();
        final double[] runningTotal = {0.0};
        TextArea area = new TextArea();
        area.setEditable(false);
        addProduct.setText("Select product to add");
        addProduct.setOnAction(_->{
            if (!(productBox.getValue() == null)){
                runningCart.add(productBox.getValue());
                area.clear();
                prc.displayPurchaseInfo(area,true);
                runningTotal[0] += productBox.getValue().getPrice();
                total.setText("€ " + runningTotal[0]);
                productBox.setValue(null);
            }
            else{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("No products");
                a.setTitle("Product/Customer not selected");
                a.show();
            }
        });
        HBox cartInfo = new HBox(area);
        cartInfo.setAlignment(Pos.CENTER);

        Button checkout = new Button();
        Button sortByPrice = new Button();
        TextArea outcome = new TextArea();
        outcome.setEditable(false);

        sortByPrice.setText("Sort by Price");

        sortByPrice.setOnAction(_->{
            prc.execSort(area,false);
        });



        checkout.setText("Checkout");
        checkout.setOnAction(_->{
            if (runningCart.isEmpty() || customersBox.getValue() == null ){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("No products/customers selected");
                a.setTitle("Product/Customer not selected");
                a.show();
            }else {
                area.clear();
                Purchase purchase = new Purchase(customersBox.getValue(), runningCart);
                total.setText("€" + String.valueOf(purchase.getTotal()));
                boolean confirmation = prc.confirmPurchase(purchase);
                if (!confirmation) {
                    outcome.appendText("Payment failed due to balance or stock issue");
                    System.out.println("their balance: €" + purchase.getBuyer().getBalance());
                    for (Product stockCheck:products){
                       System.out.println(stockCheck.toString() + " " + stockCheck.getStock());
                    }
                } else {
                    outcome.appendText("Purchase confirmed " + purchase.toString());
                    prc.recordPurchase(purchase);
                    prc.savePurchase();
                    prc.mc.pc.save();
                    prc.mc.cc.save();
                    System.out.println(purchase.toString());
                    runningCart.clear();
                }
            }});

        HBox buttons = new HBox(addProduct,checkout,sortByPrice);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);
        // Displaying info

        Label totalDescriptor = new Label("Total:");

        HBox totals = new HBox(totalDescriptor,total,outcome);
        totals.setId("totalsColor");
        totals.setAlignment(Pos.CENTER);
        totals.setSpacing(25.0);

        VBox vertical = new VBox(titleBar, topRow,cartInfo, buttons,totals,checkStock(stage,productBox));
        vertical.setSpacing(30.0);

        return vertical;
    }

    public HBox checkStock(Stage stage,ComboBox<Product> productBox){
        Label stockDisplay = new Label("");
        Button checkStock = new Button("Select Item to check");
        checkStock.setOnAction(_->{
                    prc.checkFromBox(productBox,stockDisplay);
                }
        );
        HBox stockCheckBox = new HBox(productBox,stockDisplay,checkStock);
        MainView.styleHbox(stockCheckBox,30);
        return stockCheckBox;
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
        orderInput.setText("Enter a customer ID to search:");
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

        searchOrder.setOnAction(_->{
            prc.searchForOrderById(orderInfo,Integer.parseInt(idSearchInput.getText()));
        });

        HBox orderBox = new HBox(orderInfo);
        orderBox.setAlignment(Pos.CENTER);


        HBox bottomButtons = historyButtons(stage, orderInfo);
        MainView.styleHbox(bottomButtons,20);
        VBox vertical = new VBox(titleBar, actionButtons,orderBox,bottomButtons);
        vertical.setSpacing(30.0);
        return vertical;
    }

    private HBox historyButtons(Stage stage, TextArea orderInfo) {
        Button sortOrders = new Button("Sort by date");
        Button sortByTotal = new Button("Sort by name");
        Button clear = new Button("Clear");
        clear.setOnAction(_->{
            orderInfo.setText("");
        });
        sortOrders.setOnAction(_->{
            prc.execSort(orderInfo,true);
        });

        sortByTotal.setOnAction(_->{
            prc.execSort(orderInfo);
        });

        return new HBox(sortOrders,sortByTotal,clear);
    }
}
