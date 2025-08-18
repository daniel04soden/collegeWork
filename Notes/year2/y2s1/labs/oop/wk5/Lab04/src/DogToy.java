/**
 * Our class example with a has-a relationship for the inheritance lab<br>.
 */

public class DogToy {

    //---------------------------------------
    //	Fields
    //---------------------------------------
    private String name;
    private boolean sound;


    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * The constructor creates 1 instance (1 object) of the class DogToy<br>
     * @param _name: The value of the name parameter.
     * @param _sound: The value of the sound parameter.
     */
    public DogToy(String _name, boolean _sound){
        this.name = _name;
        this.sound = _sound;
    }


    //---------------------------------------
    //	Get Methods
    //---------------------------------------
    /**
     * Given a concrete parent (this), the function returns its name.<br>
     * @return The value of name.
     */
    public String getName(){
        return this.name;
    }


    /**
     * Given a concrete parent (this), the function returns its sound.<br>
     * @return The value of sound.
     */
    public boolean getSound(){
        return this.sound;
    }


    //---------------------------------------
    //	Set Methods
    //---------------------------------------
    /**
     * Given a concrete parent (this), the function modifies its name.<br>
     * @param _name - The new value for brand.
     */
    public void setName(String _name){
        this.name = _name;
    }


    /**
     * Given a concrete parent (this), the function modifies its sound.<br>
     * @param _sound The new value for sound.
     */
    public void setSound(boolean _sound){
        this.sound = _sound;
    }

}
