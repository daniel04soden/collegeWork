package com.example.javafxassignment1ds;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ComputerApplication extends Application {
    String title;
    @Override
    public void start(Stage stage) throws IOException {
        title = "DS Computing";
        FXMLLoader fxmlLoader = new FXMLLoader(ComputerApplication.class.getResource("computer" +
                "-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        // String css = Objects.requireNonNull(this.getClass().getResource("computer.css")).toExternalForm();
        // scene.getStylesheets().add(css);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}