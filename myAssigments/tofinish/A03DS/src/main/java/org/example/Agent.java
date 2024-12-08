package org.example;

public class Agent {
    private final int id;

    /**
     * This is the constructor for the agent class which extends for the purpose of storing ids
     * @param _id
     * @return None
     */
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
