//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------


//--------------------------------------------------
//
//	INTERFACE Library
//
//--------------------------------------------------
/**
 * This is the main interface for the A03 Project Example,
 * representing the management of a library<br>.
 */
public interface Library {


    //---------------------------------------
    //	addUser
    //---------------------------------------
    /**
     * This method attempts to add a user to the library<br>
     * @param _name - The name of the user.
     * @return Whether the user could be added or not.
     */
    int addUser(String _name);


    //---------------------------------------
    //	removeUser
    //---------------------------------------
    /**
     * This method attempts to remove a user to the library<br>
     * @param _userID - The id of the user.
     * @return (-1) if user does not exist; (-2) if not possible to remove as it has active borrows; 0 if removed.
     */
    int removeUser(int _userID);


    //---------------------------------------
    //	displayUserInfo
    //---------------------------------------
    /**
     * This method attempts to display the user info by the screen<br>
     * @param _userID - The id of the user.
     */
    void displayUserInfo(int _userID);


    //---------------------------------------
    //	displayAllUserIDs
    //---------------------------------------
    /**
     * This method displays all userIDs by the screen<br>
     */
    void displayAllUserIDs();


    //---------------------------------------
    //	addBook
    //---------------------------------------
    /**
     * This method attempts to add a user to the library<br>
     * @param _title - The title of the item.
     * @param _author - The author of the item.
     * @param _releaseYear - The year the item was release.
     * @param _genre - The genre of the item.
     * @param _numPages - The number of pages of the book.
     * @return The id of the book created.
     */
    int addBook(String _title,
                String _author,
                int _releaseYear,
                String _genre,
                int _numPages
               );


    //---------------------------------------
    //	addMusicAlbum
    //---------------------------------------
    /**
     * This method attempts to add a user to the library<br>
     * @param _title - The title of the item.
     * @param _author - The author of the item.
     * @param _releaseYear - The year the item was release.
     * @param _genre - The genre of the item.
     * @param _numPages - The number of pages of the book.
     * @return The id of the book created.
     */
     int addMusicAlbum(String _title,
                       String _author,
                       int _releaseYear,
                       String _genre,
                       boolean _numPages
                      );


    //---------------------------------------
    //	removeItem
    //---------------------------------------
    /**
     * This method attempts to remove an item from the library<br>
     * @param _itemID - The id of the item.
     * @return Whether the item could be removed or not.
     */
    boolean removeItem(int _itemID);


    //---------------------------------------
    //	displayItemInfo
    //---------------------------------------
    /**
     * This method attempts to display the item info by the screen<br>
     * @param _itemID - The id of the item.
     */
    void displayItemInfo(int _itemID);


    //---------------------------------------
    //	displayAllItemIDs
    //---------------------------------------
    /**
     * This method displays all itemIDs by the screen<br>
     */
    void displayAllItemIDs();


    //---------------------------------------
    //	borrowItem
    //---------------------------------------
    /**
     * This method attempts to borrow an item of the library<br>
     * @param _userID - The id of the user borrowing the item.
     * @param _itemID - The id of the item to be borrowed.
     * @return The id of the borrow being performed (-1 if not possible to borrow).
     */
    int borrowItem(int _userID, int _itemID);


    //---------------------------------------
    //	returnItem
    //---------------------------------------
    /**
     * This method attempts to borrow an item of the library<br>
     * @param _userID - The id of the user returning the item.
     * @param _itemID - The id of the item to be returned.
     * @return Whether the item is successfully returned or not.
     */
    boolean returnItem(int _userID, int _itemID);


    //---------------------------------------
    //	imposeUserFine
    //---------------------------------------
    /**
     * This method attempts to impose a fine for a user<br>
     * @param _userID - The id of the user for which we want to impose a fine.
     * @return (-1) if user does not exist; (-2) if it was already fined; 0 if fine is imposed.
     */
    int imposeUserFine(int _userID);


    //---------------------------------------
    //	removeUserFine
    //---------------------------------------
    /**
     * This method attempts to remove the fine from a user<br>
     * @param _userID - The id of the user for which we want to remove the fine.
     * @return (-1) if user does not exist; (-2) if it was not fined; 0 if fine is removed.
     */
    int removeUserFine(int _userID);


    //---------------------------------------
    //	loadContentFromDisk
    //---------------------------------------
    /**
     * This method attempts to load the users, items and borrows of the library from disk.<br>
     * @param usersFileName - The name of the database file for users.
     * @param itemsFileName - The name of the database file for items.
     * @return Whether the content is loaded or not.
     */
    boolean loadContentFromDisk(String usersFileName, String itemsFileName);


    //---------------------------------------
    //	saveContentToDisk
    //---------------------------------------
    /**
     * This method attempts to save the users, items and borrows of the library to disk.<br>
     * @param usersFileName - The name of the database file for users.
     * @param itemsFileName - The name of the database file for items.
     * @return Whether the content is saved or not.
     */
    boolean saveContentToDisk(String usersFileName, String itemsFileName);


}
