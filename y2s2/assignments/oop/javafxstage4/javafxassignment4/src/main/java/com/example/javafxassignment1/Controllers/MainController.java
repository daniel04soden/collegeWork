package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.View.MainView;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

public class MainController implements Serializable {
    public MainView view;
    public CustomerController cc;
    public ProductController pc;
    public PurchaseController prc;
    private static final MainController mc; // Needed for singleton pattern
    public DataControllerImpl dbController = DataControllerImpl.getDbController();

    static {
        try {
            mc = new MainController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Serial
    private static final long serialVersionUID = 1;

    private MainController() throws SQLException {
        this.view = new MainView(this);
        this.cc = CustomerController.getC(this);
        this.pc = ProductController.getPc();
        this.prc = new PurchaseController(this);
        dbController.createTable();
        cc.storage = dbController.getAllCustomers();
        pc.load();
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
