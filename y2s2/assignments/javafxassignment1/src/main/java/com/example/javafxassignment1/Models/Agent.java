package com.example.javafxassignment1.Models;

public class Agent {
    private int id;
    private String name;

    // Constructors

    public Agent(int _id,String _name){
        this.id = _id;
        this.name = _name;
    }
    // Getters
    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
