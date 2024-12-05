package org.example;

public class Agent {
    private final int id;

    public Agent(int _id){
        this.id = _id;
    }

    public final int getId(){
        return this.id;
    }

    public boolean checkEquals(Agent A){
        return this.id == A.id;
    }
}
