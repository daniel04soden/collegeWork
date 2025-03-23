package com.example.javafxassignment1;

import com.example.javafxassignment1.Controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import static com.example.javafxassignment1.View.MainView.applyCSS;

public class MainApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    String title = "DS Computing Customer Management Portal";
    stage.setTitle(title);
    MainController mc = new MainController();

    TabPane mainPane = new TabPane();
    Tab tab1 = new Tab("Customers");
    tab1.setClosable(false);
    tab1.setContent(mc.cc.view.customerHome(stage));
    Tab tab2 = new Tab("Products");
    tab2.setClosable(false);
    tab2.setContent(mc.pc.view.productHome(stage));
    Tab tab3 = new Tab("Purchase");
    tab3.setClosable(false);
    tab3.setContent(mc.prc.view.mainPurchase(stage));
    mainPane.getTabs().addAll(tab1, tab2, tab3); // Add the tabs to the tab pane.

    SplitPane root = new SplitPane(mainPane);
    Scene main = new Scene(root,1000,500);
    applyCSS(main);
    stage.setScene(main);
    stage.setResizable(false);
    stage.show();
    stage.setOnCloseRequest(event -> {
      event.consume();
      mc.view.closeConfirmation(stage);
    });

  }

  public static void main(String[] args) {
    launch();
  }
}
