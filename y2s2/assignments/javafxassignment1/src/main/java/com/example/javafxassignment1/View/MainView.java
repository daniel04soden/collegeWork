package com.example.javafxassignment1.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class MainView {

    // Custom buttons

    // Back button

    public static Button backBtn(Stage stage, Scene previous) {
        Button backBtn = new Button();
        backBtn.setText("Back");

        backBtn.setOnAction(_ -> stage.setScene(previous));
        return backBtn;
    }
}
