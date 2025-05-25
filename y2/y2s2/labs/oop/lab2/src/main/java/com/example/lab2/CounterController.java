package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class CounterController {
    @FXML
    private TextField displayOfValue;
    public static int value = 0;
    @FXML
    protected void onCounterClick() {
        displayOfValue.setText(String.valueOf(value));
        value++;
    }

}