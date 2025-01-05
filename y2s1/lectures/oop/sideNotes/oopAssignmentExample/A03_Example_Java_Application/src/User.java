//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.ArrayList;

//--------------------------------------------------
//
//	CLASS User
//
//--------------------------------------------------
/**
 * This class models a user of the library<br>.
 */
public class User extends Agent {

    //---------------------------------------
    //	Fields
    //---------------------------------------
    private final String name;
    private boolean isFined;
    private final ArrayList<Borrow> activeBorrows;
    private final int maxBorrows;

    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * The constructor creates 1 instance (1 object) of the class User<br>
     * @param _id - The ID of the user.
     * @param _name - The name of the user.
     * @param _maxBorrows - The maximum borrows a user can make.
     */
    public User(int _id,
                String _name,
                int _maxBorrows
               ){

        // 1. We call to the agent, to initialise the id and the borrowHistory
        super(_id);

        // 2. We initialise the remaining fields
        this.name = _name;
        this.maxBorrows = _maxBorrows;

        // 3. The new user has not borrowed any item yet.
        this.isFined = false;
        this.activeBorrows = new ArrayList<Borrow>();
    }


    //---------------------------------------
    //	GET METHODS
    //---------------------------------------


    //---------------------------------------
    //	getIsFined
    //---------------------------------------
    /**
     * Given a concrete user (this), the function returns if the user is fined.<br>
     * @return Whether the user is fined or not.
     */
    public boolean getIsFined(){
        return this.isFined;
    }


    //---------------------------------------
    //	getActiveBorrows
    //---------------------------------------
    /**
     * Given a concrete user (this), the function returns the active borrows of the user.<br>
     * @return The active borrows of the user.
     */
    public ArrayList<Borrow> getActiveBorrows(){
        return this.activeBorrows;
    }


    //---------------------------------------
    //	getMaxBorrows
    //---------------------------------------
    /**
     * Given a concrete user (this), the function returns the max borrows of the user.<br>
     * @return The max borrows of the user.
     */
    public int getMaxBorrows(){
        return this.maxBorrows;
    }


    //---------------------------------------
    //	SET METHODS
    //---------------------------------------


    //---------------------------------------
    //	setIsFined
    //---------------------------------------
    /**
     * Given a concrete user (this), the function updates whether the user is fined or not.<br>
     * @param _isFined - Whether the user is fined or not.
     */
    public void setIsFined(boolean _isFined){
        this.isFined = _isFined;
    }


    //---------------------------------------
    //	EXTRA METHODS
    //---------------------------------------


    //---------------------------------------
    //	toString
    //---------------------------------------
    /**
     * Given a concrete user (this), the function overrides the Object method toString.<br>
     */
    public String toString(){
        // 1. We create the output variable
        String res;

        // 2. We append the user info
        res = "" + this.getId() + "\t" + this.name + "\t" + this.isFined + "\t" + this.maxBorrows + "\n";

        int size = this.activeBorrows.size();
        res = res + size + "\n";
        for (Borrow activeBorrow : this.activeBorrows) {
            res = res + activeBorrow.toString();
        }

        // 3. We return res
        return res;
    }


    //---------------------------------------
    //	isUserInUsersList
    //---------------------------------------
    /**
     * This method attempts to find it a user is in the usersList<br>
     * @param id - The id of the user.
     * @param myList - The list of users where we are looking for.
     * @return The index in myList where the agent with id is located at; (-1) if it is not in the list.
     */
    public static int isUserInUsersList(int id, ArrayList<User> myList){
        // 1. We create the output variable
        int res = -1;

        // 2. We create the agent from the id
        Agent baseline = new Agent(id);

        // 2. We search for the agent in the list
        int size = myList.size();
        int index = 0;

        // 3. If we found the userID in the list, we assign its index to res
        while ((res == -1) && (index < size)){
            if (baseline.equals(myList.get(index)))
                res = index;
            else
                index++;
        }

        // 4. We return res
        return res;
    }


}
