package com.example.javafxassignment1.View;

import com.example.javafxassignment1.Controllers.MainController;
import com.example.javafxassignment1.MainApplication;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MainView {
    /*
     * The mainview class does not need any kind of controllers as this
     * class simply allows for static use of various different ui elements like
     * buttons and pop-ups
     * */
    MainController mc ;
    public MainView(MainController mc_){
        this.mc = mc_;
    }

    public static void applyCSS(Scene styledScene) {
        String cssSource = "styles.css";
        styledScene.getStylesheets().add(Objects.requireNonNull(MainApplication.class.getResource(cssSource)).toExternalForm());
    }

    public static void updateContent(SplitPane splitPane, VBox newContent) {
        splitPane.getItems().set(1, newContent);
    }

    public void closeConfirmation(Stage stage) {
        // Function to prevent instant closing and check if the user has saved or not
        ButtonType saveAndExit = new ButtonType("Save and Exit?");
        ButtonType saveNoExit = new ButtonType("Don't save?");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);



        System.out.println("Attempting to close window");
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("You sure you want to quit?");
        closeAlert.getButtonTypes().setAll(saveAndExit,saveNoExit,cancel);

        ButtonType finalChoice = closeAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (finalChoice == saveAndExit){
            stage.close();
            mc.cc.save();
            mc.pc.save();
            mc.prc.savePurchase();
        }else if (finalChoice == saveNoExit){
            stage.close();
        }
    }
    public static void styleHbox(HBox box,double spacing){
        box.setSpacing(spacing);
        box.setAlignment(Pos.CENTER);
    }

    public static void validateText(TextField t,Label display){
        String myVal = t.getText().trim();
        if (myVal.isEmpty()){
            display.setText("Fields cannot be empty");
        }else{
           display.setText("");
        }
    }
    public static void displaySave(){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String successMsg = "Successfully saved and serialized data";
            a.setTitle(successMsg);
            a.setContentText(successMsg);
        a.show();
    }
    public static void displayLoad(){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String successMsg = "Loaded data and deserialized, any unsaved data has been lost";
            a.setTitle(successMsg);
            a.setContentText(successMsg);
        a.show();
    }

}
