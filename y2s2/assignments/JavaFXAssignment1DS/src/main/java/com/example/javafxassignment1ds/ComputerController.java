package com.example.javafxassignment1ds;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ComputerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Login to our epic JavaFx system");
    }
}