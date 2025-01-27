package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Label enterField;
    private TextField numInput;
    priavte sum;

    @FXML
    protected void onHelloButtonClick() {
        enterField.setText("Enter an integer");

    }
}