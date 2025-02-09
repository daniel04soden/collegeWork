package com.example.javafxassignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
public class MainApplication extends Application {
    String customerLocation = "./database/customers.txt";

    public Scene homePage(BorderPane root){
        Scene home = new Scene(root,1000,500);
        Label test= new Label();
        test.setText("DS Computing");
        HBox titleBar = new HBox(test);
        titleBar.setAlignment(Pos.CENTER);
        VBox vertical = new VBox(titleBar);

        root.setCenter(vertical);

        return home;
    }


    @Override
    public void start(Stage stage) throws Exception {
        String title = "DS Computing";
        stage.setTitle(title);
        BorderPane root = new BorderPane();
        Scene home = homePage(root);
        stage.setScene(home);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}