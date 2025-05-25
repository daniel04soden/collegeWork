package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Store;
import com.example.javafxassignment1.View.MainView;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;

public class MainController implements Serializable {
    public MainView view;
    public CustomerController cc;
    public ProductController pc;
    public PurchaseController prc;
    private static MainController mc;

    public DataControllerImpl dbController;
    public Store s;

    @Serial
    private static final long serialVersionUID = 1;

    private MainController() throws SQLException {
        this.view = new MainView(this);
        this.cc = CustomerController.getC(this);
        this.pc = ProductController.getP(this);
        this.prc = new PurchaseController(this);
        dbController = DataControllerImpl.getDbController();
        dbController.initTables();
        cc.loadCustomersFromDb();
        pc.loadProductsFromDb();
        prc.loadInPurchases();
        s = Store.getStore(this); // Pass the MainController instance
    }

    public static MainController getMc() {
        if (mc == null) {
            try {
                mc = new MainController();
            } catch (SQLException e) {
                throw new RuntimeException("Error initializing MainController", e);
            }
        }
        return mc;
    }

    public static boolean checkFile(String dbpath){
        File file = new File(dbpath);
        return file.exists();
    }
}