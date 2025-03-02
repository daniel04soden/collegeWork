package com.example.javafxassignment1.Models;

import java.util.Objects;

public class Record {
    private final int id;
    private final String description;

    public Record(int _id, String _description){
       this.id = _id;
       this.description = _description;
    }

    public int getId(){
        return this.id;
    }
    public String getDescription(){
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id && Objects.equals(description, record.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    // No setters as the fields are final
}
