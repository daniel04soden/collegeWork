package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Purchase;
import com.example.javafxassignment1.View.MainView;

import java.util.ArrayList;

public class MainController {
    public MainView view;
    public CustomerController cc;
    public ProductController pc;
    public PurchaseController prc;

    public MainController() {
        this.view = new MainView(this);
        this.cc = new CustomerController(this);
        this.pc = new ProductController(this);
        this.prc = new PurchaseController(this);

        cc.load();
        pc.load();
    }
}
