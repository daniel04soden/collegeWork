
/**
 * Our class for the has-a relationship<br>.
 */

public class Toy {

    //---------------------------------------
    //	Fields
    //---------------------------------------
    private boolean sound;
    private int resistance;


    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * The constructor creates 1 instance (1 object) of the class Toy<br>
     * @param b: The value of the sound parameter.
     * @param i: The value of the resistance parameter.
     */
    public Toy(boolean b, int i){
        this.sound = b;
        this.resistance = i;
    }


    //---------------------------------------
    //	Get Methods
    //---------------------------------------
    /**
     * Given a concrete parent (this), the function returns whether it sounds or not.<br>
     * @return The value of sound.
     */
    public boolean getSound(){
        return this.sound;
    }


    /**
     * Given a concrete parent (this), the function returns its resistance.<br>
     * @return The value of resistance.
     */
    public int getResistance(){
        return this.resistance;
    }


    //---------------------------------------
    //	Set Methods
    //---------------------------------------
    /**
     * Given a concrete parent (this), the function modifies its sound.<br>
     * @param b The new value for sound.
     */
    public void setSound(boolean b){
        this.sound = b;
    }


    /**
     * Given a concrete parent (this), the function modifies its model.<br>
     * @param i The new value for model.
     */
    public void setResistance(int i){
        this.resistance = i;
    }


}
