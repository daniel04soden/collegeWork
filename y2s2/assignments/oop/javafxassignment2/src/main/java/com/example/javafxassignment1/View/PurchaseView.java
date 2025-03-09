package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.CustomerController;
import com.example.javafxassignment1.Controllers.PurchaseController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.javafxassignment1.View.MainView.applyCSS;
import static com.example.javafxassignment1.View.MainView.backBtn;

public class PurchaseView {
    PurchaseController controller;

    public PurchaseView(PurchaseController controller_){
        this.controller = controller_;
    }
    public BorderPane mainPurchase(Stage stage) {

        BorderPane root = new BorderPane();
        // Init the scene


        // Header Bar
        Label title = new Label();
        title.setText("DS Computing: Buy a computer");
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        stage.setTitle(title.getText());

        // Name Hbox label and input

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(25.0);

        // put all into this vbox

        VBox vertical = new VBox(titleBar,buttons);
        vertical.setAlignment(Pos.CENTER);
        vertical.setSpacing(30.0);

        root.setCenter(vertical);
        return root;
    }
}
