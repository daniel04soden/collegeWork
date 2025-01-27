package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IncrementButton {
    @FXML
    private Label buttonText;
    private int counter;

    @FXML
    protected void onHelloButtonClick() {
        counter++;
        buttonText.setText(String.valueOf(counter));
    }
}