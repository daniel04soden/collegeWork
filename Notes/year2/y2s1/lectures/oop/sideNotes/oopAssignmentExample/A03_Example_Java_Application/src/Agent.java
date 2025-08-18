//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.ArrayList;

//--------------------------------------------------
//
// CLASS Agent
//
//--------------------------------------------------
/**
 * Our superclass for the A03 Project Example
 * representing all the agents (users and items)
 * for managing the library <br>.
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
     * The constructor creates 1 instance (1 object) of the class Agent<br>
     * @param _id - The ID of the agent.
     */
    public Agent(int _id) {
        // 1. We initialise the fields
        this.id = _id;
    }


    //---------------------------------------
    //	GET METHODS
    //---------------------------------------


    //---------------------------------------
    //	getId
    //---------------------------------------
    /**
     * Given a concrete agent (this), the function returns its id.<br>
     * We make the method final, to avoid the subclasses to override it.
     * @return The id of the agent.
     */
    public final int getId(){
        return this.id;
    }


    //---------------------------------------
    //	equals
    //---------------------------------------
    /**
     * Given a concrete agent (this), the function overrides the Object method equals.<br>
     * @param o - The other agent we want to compare 'this' to.
     * @return - Whether 'this' and 'o' are equal.
     */
    public boolean equals(Agent o) {
        return this.id == o.id;
    }


}
