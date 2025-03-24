package com.example.javafxassignment1.Models;

import java.io.Serial;
import java.io.Serializable;

public class Agent implements Serializable {
    private int id;
    private String name;

    @Serial
    private static final long serialVersionUID = 1;
    // Constructors
    public Agent(){
    }
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
