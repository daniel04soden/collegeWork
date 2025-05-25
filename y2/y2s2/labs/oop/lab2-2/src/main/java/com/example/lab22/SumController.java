package com.example.lab22;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SumController {
    private int sum;
    @FXML
    private int newVal;
    @FXML
    private TextField sumVal;
    @FXML
    private TextField currentSumDisplay;

    @FXML
    public void increaseSum(){
        newVal = Integer.parseInt(sumVal.getText());
        sum+= newVal;
        currentSumDisplay.setText(String.valueOf(sum));
        sumVal.clear();
    }
}