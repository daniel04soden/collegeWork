package com.example.lab23;

// Java FX Imports
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

// Java Standard inputs
import java.util.Random;


public class HelloController {
    @FXML
    private TextArea inputGuess;
    private int answer;


    @FXML
    protected void onHelloButtonClick() {
        Random randomInit = new Random();
        int randint = randomInit.nextInt(100);


    }
}