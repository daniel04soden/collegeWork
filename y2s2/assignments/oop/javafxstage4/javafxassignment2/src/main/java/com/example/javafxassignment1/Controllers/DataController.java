package com.example.javafxassignment1.Controllers;

public class DataController {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private DataController d = new DataController(url);
    private DataController(String url_){ // Singleton pattern

    }


    public DataController getD() {
        return d;
    }

    public void setD(DataController d) {
        this.d = d;
    }
}
