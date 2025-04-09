package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.View.MainView;

import java.io.File;
import java.sql.SQLException;

public class MainController {
    public MainView view;
    public CustomerController cc;
    public ProductController pc;
    public PurchaseController prc;
    public DataController dc;

    public MainController() throws SQLException {
        this.view = new MainView(this);
        this.cc = new CustomerController(this);
        this.pc = new ProductController(this);
        this.prc = new PurchaseController(this);
        this.dc = new DataController();


        pc.load();
        cc.load();
        prc.loadInPurchases();
        dc.getConnection();
    }

    public static boolean checkFile(String dbpath){
       File file = new File(dbpath);
        return file.exists();
    }
}
