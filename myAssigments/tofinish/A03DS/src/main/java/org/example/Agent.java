package org.example;
//--------------------------------------------------
// IMPORTS
//--------------------------------------------------

//--------------------------------------------------
//
// CLASS Agent
//
//--------------------------------------------------

/**
 * Our Superclass used for the project which
 * models Products,computers and users
 * for managing our shop <br>
 */
public class Agent {

    //---------------------------------------
    //	Fields
    //---------------------------------------

    private final int id;


    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * This is the constructor for the agent class which extends for the purpose of storing ids
     * @param _id - Id of the agent
     */
    public Agent(int _id){
        this.id = _id;
    }

    //---------------------------------------
    //	GET METHODS
    //---------------------------------------

    //---------------------------------------
    //	getId
    //---------------------------------------
    /**
     * @return the id of the agent class
     */
    public final int getId(){
        return this.id;
    }

    /**
     * Given a concrete agent thism the function overrides the object
     * method equals <br>
     * @param - A Agent Object to compare this to
     * @return - boolean true or false if this is the same as the given Object
     */
    public boolean checkEquals(Agent A){
        return this.id == A.id;
    }
}
