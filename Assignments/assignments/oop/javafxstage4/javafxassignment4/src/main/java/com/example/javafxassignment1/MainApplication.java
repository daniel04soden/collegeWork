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
  private final MainController mc = MainController.getMc(); // Singleton Pattern
  
  public SplitPane init(Stage stage){
    TabPane mainPane = new TabPane();
    Tab tab1 = new Tab("Customers");
    tab1.setClosable(false);
    SplitPane initCustomer = mc.cc.view.splitInit(stage);
    tab1.setContent(initCustomer);
    Tab tab2 = new Tab("Purchase");
    tab2.setClosable(false);
    SplitPane initPurchase = mc.prc.view.splitInit(stage);
    tab2.setContent(initPurchase);
    Tab tab3 = new Tab("Maintenance");
    tab3.setClosable(false);
    SplitPane initProduct = mc.pc.view.splitInit(stage);
    double spacing = 0.35;
    initCustomer.setDividerPositions(spacing);
    initPurchase.setDividerPositions(spacing); 
    initProduct.setDividerPositions(spacing);
    tab3.setContent(initProduct);
    mainPane.getTabs().addAll(tab1, tab2, tab3); // Add the tabs to the tab pane.

    return new SplitPane(mainPane);
  }
    @Override
  public void start(Stage stage) {
    String title = "DS Computing Customer Management Portal";
    stage.setTitle(title);
    SplitPane root = init(stage);
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
