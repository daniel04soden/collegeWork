package com.example.oopstage2;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.oopstage2.Controller.*;

import java.io.IOException;

public class ComputerApplication extends Application {
    private startController controller;

    public ComputerApplication(){
        this.controller = new CustomerController();
    }
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("DS Computing");
        stage.setScene(start);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}