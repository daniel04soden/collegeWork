//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------


//--------------------------------------------------
//
//	CLASS User
//
//--------------------------------------------------

import java.util.ArrayList;

/**
 * This class models a borrow for the library<br>.
 */
public class Borrow {

    //---------------------------------------
    //	Fields
    //---------------------------------------
    private final int borrowID;
    private final int userID;
    private final int itemID;
    private final int numDays;

    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * The constructor creates 1 instance (1 object) of the class User<br>
     * @param _borrowID - The ID of the borrow.
     * @param _userID - The ID of the user.
     * @param _itemID - The ID of the item.
     * @param _numDays - The number of days the item can be borrowed.
     */
    public Borrow(int _borrowID,
                  int _userID,
                  int _itemID,
                  int _numDays
                 ){

        // 1. We initialise the fields
        this.borrowID = _borrowID;
        this.userID = _userID;
        this.itemID = _itemID;
        this.numDays = _numDays;
    }


    //---------------------------------------
    //	GET METHODS
    //---------------------------------------


    //---------------------------------------
    //	getItemID
    //---------------------------------------
    /**
     * Given a concrete borrow (this), the function returns the id of its item.<br>
     * @return The id of the item.
     */
    public int getItemID(){
        return this.itemID;
    }


    //---------------------------------------
    //	EXTRA METHODS
    //---------------------------------------


    //---------------------------------------
    //	toString
    //---------------------------------------
    /**
     * Given a concrete borrow (this), the function overrides the Object method toString.<br>
     */
    public String toString(){
        // 1. We create the output variable
        String res;

        // 2. We append the borrow info
        res = "" + this.borrowID + "\t" + this.userID + "\t" + this.itemID + "\t" + this.numDays + "\n";

        // 3. We return res
        return res;
    }


    //---------------------------------------
    //	isItemInBorrowsList
    //---------------------------------------
    /**
     * This method attempts to find it an item is in the borrowsList<br>
     * @param id - The id of the item.
     * @param myList - The list of borrows where we are looking for.
     * @return The index in myList where the agent with id is located at; (-1) if it is not in the list.
     */
    public static int isItemInBorrowsList(int id, ArrayList<Borrow> myList){
        // 1. We create the output variable
        int res = -1;

        // 2. We search for the item in the list
        int size = myList.size();
        int index = 0;

        // 3. If we found the itemID in the list, we assign its index to res
        while ((res == -1) && (index < size)){
            if (myList.get(index).getItemID() == id)
                res = index;
            else
                index++;
        }

        // 4. We return res
        return res;
    }

}
