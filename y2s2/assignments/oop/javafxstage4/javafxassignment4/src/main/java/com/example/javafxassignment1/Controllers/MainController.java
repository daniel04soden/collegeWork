package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.View.MainView;

import java.io.File;

public class MainController {
    public MainView view;
    public CustomerController cc;
    public ProductController pc;
    public PurchaseController prc;
    private static final MainController mc = new MainController(); // Needed for singleton pattern

    private MainController() {
        this.view = new MainView(this);
        this.cc = CustomerController.getC();
        this.pc = new ProductController(this);
        this.prc = new PurchaseController(this);

        pc.load();
        cc.load();
        prc.loadInPurchases();
    }

    public static boolean checkFile(String dbpath){
       File file = new File(dbpath);
        return file.exists();
    }


    public static MainController getMc() {
        return mc;
    }
}
