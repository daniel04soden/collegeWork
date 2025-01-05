//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;


//--------------------------------------------------
//
//	CLASS LibraryImp
//
//--------------------------------------------------
/**
 * This class implements the interface Library of the A03 Project Example,
 * representing the management of a library<br>.
 * We make the class final, to avoid any further class specialising it more.
 */
public final class LibraryImp implements Library {


    //---------------------------------------
    //	Fields
    //---------------------------------------
    private final ArrayList<User> usersList;
    private final ArrayList<Item> itemsList;
    private final int maxBorrowItems;

    private static int nextId = 1;

    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * The constructor creates 1 instance (1 object) of the class LibraryImp<br>
     * @param _maxBorrowItems - The maximum amount of items a user can borrow.
     */
    public LibraryImp(int _maxBorrowItems){
        // 1. We initialise the fields
        this.maxBorrowItems = _maxBorrowItems;

        // 2. We assume the library does not have any user or item
        this.usersList = new ArrayList<User>();
        this.itemsList = new ArrayList<Item>();
    }

    //---------------------------------------
    //	addUser
    //---------------------------------------
    /**
     * This method attempts to add a user to the library<br>
     * @param _name - The name of the user.
     * @return The id of the user created.
     */
    public int addUser(String _name){
        // 1. We create the output variable
        int res = LibraryImp.nextId;

        // 2. We create the new user object
        User newUser = new User(res,
                                _name,
                                this.maxBorrowItems
                               );

        // 3. We add it to the library list
        this.usersList.add(newUser);

        // 4. We increase the static variable of the class
        LibraryImp.nextId++;

        // 5. We return res
        return res;
    }


    //---------------------------------------
    //	removeUser
    //---------------------------------------
    /**
     * This method attempts to remove a user to the library<br>
     * @param _userID - The id of the user.
     * @return (-1) if user does not exist; (-2) if not possible to remove as it has active borrows; 0 if removed.
     */
    public int removeUser(int _userID){
        // 1. We create the output variable
        int res = -1;

        // 1. We search for the userID in the usersList
        int index = User.isUserInUsersList(_userID, this.usersList);

        // 3. If the index is not -1
        if (index != -1){
            // 3.1. We check the pending borrows of the user
            User myUser = this.usersList.get(index);
            int size = myUser.getActiveBorrows().size();

            // 3.2. If there are no pending borrows, we remove the user
            if (size == 0){
                res = 0;
                this.usersList.remove(index);
            }

            // 3.3. Otherwise, we flag that there are pending borrows
            else{
                res = -2;
            }
        }

        // 4. We return res
        return res;
    }


    //---------------------------------------
    //	displayUserInfo
    //---------------------------------------
    /**
     * This method attempts to display the user info by the screen<br>
     * @param _userID - The id of the user.
     */
    public void displayUserInfo(int _userID){
        // 1. We search for the userID in the usersList
        int index = User.isUserInUsersList(_userID, this.usersList);

        // 2. If the index is not -1
        if (index != -1){
            // 2.1. We get the user
            User myUser = this.usersList.get(index);

            // 2.2. We get the info
            String info = myUser.toString();

            // 2.3. We print the info
            System.out.println(info);
        }

        // 3. Otherwise, we inform that the user id is not registered
        else
            System.out.println("Sorry, no user is registered with id = " + _userID + " so the remove operation cannot proceed.");

    }


    //---------------------------------------
    //	displayAllUserIDs
    //---------------------------------------
    /**
     * This method displays all userIDs by the screen<br>
     */
    public void displayAllUserIDs(){
        System.out.println("---------------\n  User IDs\n---------------");
        for (User aux : this.usersList) {
            System.out.println(aux.getId());
        }
    }


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
    public int addBook(String _title,
                       String _author,
                       int _releaseYear,
                       String _genre,
                       int _numPages
                      ){

        // 1. We create the output variable
        int res = LibraryImp.nextId;

        // 2. We create the new book object
        Book newBook = new Book(res,
                                _title,
                                _author,
                                _releaseYear,
                                _genre,
                                _numPages
                               );

        // 3. We add it to the library list
        this.itemsList.add(newBook);

        // 4. We increase the static variable of the class
        LibraryImp.nextId++;

        // 5. We return res
        return res;
    }


    //---------------------------------------
    //	addMusicAlbum
    //---------------------------------------
    /**
     * This method attempts to add a user to the library<br>
     * @param _title - The title of the item.
     * @param _author - The author of the item.
     * @param _releaseYear - The year the item was release.
     * @param _genre - The genre of the item.
     * @param _isAlbumOfYear - Whether the album was named album of the year when released.
     * @return The id of the music album created.
     */
    public int addMusicAlbum(String _title,
                             String _author,
                             int _releaseYear,
                             String _genre,
                             boolean _isAlbumOfYear
                            ){

        // 1. We create the output variable
        int res = LibraryImp.nextId;

        // 2. We create the new book object
        MusicAlbum newMusicAlbum = new MusicAlbum(res,
                                                  _title,
                                                  _author,
                                                  _releaseYear,
                                                  _genre,
                                                  _isAlbumOfYear
                                                 );

        // 3. We add it to the library list
        this.itemsList.add(newMusicAlbum);

        // 4. We increase the static variable of the class
        LibraryImp.nextId++;

        // 5. We return res
        return res;
    }


    //---------------------------------------
    //	removeItem
    //---------------------------------------
    /**
     * This method attempts to remove an item from the library<br>
     * @param _itemID - The id of the item.
     * @return Whether the item could be removed or not.
     */
    public boolean removeItem(int _itemID){
        // 1. We create the output variable
        boolean res = false;

        // 1. We search for the userID in the usersList
        int index = Item.isItemInItemsList(_itemID, this.itemsList);

        // 3. If the index is not -1
        if (index != -1){
            res = true;
            this.itemsList.remove(index);
        }

        // 4. We return res
        return res;
    }


    //---------------------------------------
    //	displayItemInfo
    //---------------------------------------
    /**
     * This method attempts to display the item info by the screen<br>
     * @param _itemID - The id of the item.
     */
    public void displayItemInfo(int _itemID){
        // 1. We search for the itemID in the itemsList
        int index = Item.isItemInItemsList(_itemID, this.itemsList);

        // 2. If the index is not -1
        if (index != -1){
            // 2.1. We get the user
            Item myItem = this.itemsList.get(index);

            // 2.2. We get the info
            String info = myItem.toString();

            // 2.3. We print the info
            System.out.println(info);
        }

        // 3. Otherwise, we inform that the user id is not registered
        else
            System.out.println("Sorry, no item is registered with id = " + _itemID + " so the remove operation cannot proceed.");


    }


    //---------------------------------------
    //	displayAllItemIDs
    //---------------------------------------
    /**
     * This method displays all itemIDs by the screen<br>
     */
    public void displayAllItemIDs(){
        System.out.println("---------------\n  Item IDs\n---------------");
        for (Item aux : this.itemsList) {
            System.out.println(aux.getId());
        }
    }


    //---------------------------------------
    //	borrowItem
    //---------------------------------------
    /**
     * This method attempts to borrow an item of the library<br>
     * @param _userID - The id of the user borrowing the item.
     * @param _itemID - The id of the item to be borrowed.
     * @return The id of the borrow being performed (-1 if not possible to borrow).
     */
    public int borrowItem(int _userID, int _itemID){
        // 1. We create the output variable
        int res = -1;

        // 2. We get the index of the user and the item in userList and itemsList
        int userIndex = User.isUserInUsersList(_userID, this.usersList);
        if (userIndex == -1)
            System.out.println("Sorry, there is no user with " + _userID + " in the UsersList");

        int itemIndex = Item.isItemInItemsList(_itemID, this.itemsList);
        if (itemIndex == -1)
            System.out.println("Sorry, there is no item with " + _itemID + " in the ItemsList");

        // 3. If both user and item exist, we need to ensure that:
        //    (a) the user is not fined,
        //    (b) the user can still borrow more items,
        //    (c) the item is available
        if ((userIndex >= 0) && (itemIndex >= 0)){
            // 3.1. We access to the user and item
            User myUser = this.usersList.get(userIndex);
            Item myItem = this.itemsList.get(itemIndex);

            // 3.2. We check whether the user is fined
            boolean isFined = myUser.getIsFined();


            // 3.3. We get the number of borrows of the user
            int numBorrows = myUser.getActiveBorrows().size();
            int maxBorrows = myUser.getMaxBorrows();

            // 3.4. We check that the item is not borrowed
            Borrow itemBorrowObject = myItem.getCurrentBorrowed();

            // 3.5. If the conditions are met, we proceed to borrow the item
            if (!isFined)
                if (numBorrows < maxBorrows)
                   if (itemBorrowObject == null)
                       res = auxBorrowItem(myUser, myItem);
                   else
                       System.out.println("Sorry, the item with id " + _itemID + " is already borrowed.");
                else
                    System.out.println("Sorry, the user with id " + _userID + " has already " + maxBorrows + " items borrowed.");
            else
                System.out.println("Sorry, the user with id " + _userID + " is currently fined.");
        }

        // 4. We return res
        return res;
    }


    //---------------------------------------
    //	auxBorrowItem
    //---------------------------------------
    /**
     * This method proceeds with the borrowing of the item<br>
     * @param myUser - The user borrowing the item.
     * @param myItem - The item to be borrowed.
     * @return The id of the borrow being performed.
     */
    private int auxBorrowItem(User myUser, Item myItem) {
        // 1. We create the output variable
        int res = LibraryImp.nextId;

        // 2. We update the id
        LibraryImp.nextId++;

        // 3. We get the number of days for the item to be borrowed
        int numDays = myItem.numDaysToBorrow();

        // 4. We create the borrow object
        Borrow myBorrow = new Borrow(res,
                                     myUser.getId(),
                                     myItem.getId(),
                                     numDays
                                    );

        // 5. We update the user by adding the borrow to its active borrows
        myUser.getActiveBorrows().add(myBorrow);

        // 6. We update the item by setting the borrow as its active borrow
        myItem.setCurrentBorrowed(myBorrow);

        // 7. We return res
        return res;
    }


    //---------------------------------------
    //	returnItem
    //---------------------------------------
    /**
     * This method attempts to borrow an item of the library<br>
     * @param _userID - The id of the user returning the item.
     * @param _itemID - The id of the item to be returned.
     * @return Whether the item is successfully returned or not.
     */
    public boolean returnItem(int _userID, int _itemID){
        // 1. We create the output variable
        boolean res = false;

        // 2. We get the index of the user and the item in userList and itemsList
        int userIndex = User.isUserInUsersList(_userID, this.usersList);
        if (userIndex == -1)
            System.out.println("Sorry, there is no user with " + _userID + " in the UsersList");

        int itemIndex = Item.isItemInItemsList(_itemID, this.itemsList);
        if (itemIndex == -1)
            System.out.println("Sorry, there is no item with " + _itemID + " in the ItemsList");

        // 3. If both user and item exist, we need to ensure that the user is borrowing the item
        if ((userIndex >= 0) && (itemIndex >= 0)){
            // 3.1. We access to the user
            User myUser = this.usersList.get(userIndex);
            Item myItem = this.itemsList.get(itemIndex);

            // 3.2. We search for the item in its borrows list
            ArrayList<Borrow> borrowList = myUser.getActiveBorrows();
            int index = Borrow.isItemInBorrowsList(_itemID, borrowList);

            // 3.3. If the conditions are met, we proceed to return the item
            if (index != -1) {
                // I. We set res to true
                res = true;

                // II. We remove the borrow from the user
                borrowList.remove(index);

                // III. We set the item to not to be borrowed
                myItem.setCurrentBorrowed(null);
            }
            else
                System.out.println("Sorry, the user with id " + _userID + " was not borrowing the item with id " + _itemID + "");
        }

        // 4. We return res
        return res;
    }


    //---------------------------------------
    //	imposeUserFine
    //---------------------------------------
    /**
     * This method attempts to impose a fine for a user<br>
     * @param _userID - The id of the user for which we want to impose a fine.
     * @return (-1) if user does not exist; (-2) if it was already fined; 0 if fine is imposed.
     */
    public int imposeUserFine(int _userID){
        // 1. We create the output variable
        int res = -1;

        // 2. We search for the userID in the usersList
        int index = User.isUserInUsersList(_userID, this.usersList);

        // 3. If the index is not -1
        if (index != -1){
            // 3.1. We check if the user was fined
            User myUser = this.usersList.get(index);
            boolean isFined = myUser.getIsFined();

            // 3.2. If it was already fined
            if (isFined)
                res = -2;
            else{
                res = 0;
                myUser.setIsFined(true);
            }
        }

        // 4. We return res
        return res;
    }


    //---------------------------------------
    //	removeUserFine
    //---------------------------------------
    /**
     * This method attempts to remove the fine from a user<br>
     * @param _userID - The id of the user for which we want to remove the fine.
     * @return (-1) if user does not exist; (-2) if it was not fined; 0 if fine is removed.
     */
    public int removeUserFine(int _userID){
        // 1. We create the output variable
        int res = -1;

        // 2. We search for the userID in the usersList
        int index = User.isUserInUsersList(_userID, this.usersList);

        // 3. If the index is not -1
        if (index != -1){
            // 3.1. We check if the user was fined
            User myUser = this.usersList.get(index);
            boolean isFined = myUser.getIsFined();

            // 3.2. If it was already fined
            if (!isFined)
                res = -2;
            else{
                res = 0;
                myUser.setIsFined(false);
            }
        }

        // 4. We return res
        return res;
    }


    //---------------------------------------
    //	loadContentFromDisk
    //---------------------------------------
    /**
     * This method attempts to load the users, items and borrows of the library from disk.<br>
     * @param usersFileName - The name of the database file for users.
     * @param itemsFileName - The name of the database file for items.
     * @return Whether the content is loaded or not.
     */
    public boolean loadContentFromDisk(String usersFileName,
                                       String itemsFileName
                                      ){

        return ((this.loadUsersFromDisk(usersFileName)) &&
                (this.loadItemsFromDisk(itemsFileName))
               );

    }


    //---------------------------------------
    //	loadUsersFromDisk
    //---------------------------------------
    /**
     * This method attempts to load the users from disk.<br>
     * @param usersFileName - The name of the database file for users.
     */
    private boolean loadUsersFromDisk(String usersFileName){
        // 1. We create the output variable
        boolean res = false;

        try {
            // 1. We create the file variable
            File my_file = new File(usersFileName);
            Scanner sc = new Scanner(my_file);

            // 2. If the file is not empty
            if (sc.hasNext()){
                // 2.1. We get the number of users
                int usersSize = sc.nextInt();

                // 2.2. We parse each of the user objects
                for (int userIndex = 0; userIndex < usersSize; userIndex++){
                    // 2.2.1. We read the values for the object
                    int _id = sc.nextInt();
                    String _name = sc.next();
                    boolean _isFined = sc.nextBoolean();
                    int _maxBorrows = sc.nextInt();

                    // 2.2.2. We create the object
                    User myUser = new User(_id,
                                           _name,
                                           _maxBorrows
                                          );

                    // 2.2.3. We set the user to fined
                    myUser.setIsFined(_isFined);

                    // 2.2.4. We read how many active borrows
                    int numBorrows = sc.nextInt();

                    // 2.2.5. If the id is bigger than the existing one, we update it
                    if (_id >= LibraryImp.nextId)
                        LibraryImp.nextId = _id + 1;

                    // 2.2.6. For each borrow, we read it
                    for (int borrowIndex = 0; borrowIndex < numBorrows; borrowIndex++){
                        // I. We read the values for the object
                        int _borrowID = sc.nextInt();
                        int _userID = sc.nextInt();
                        int _itemID = sc.nextInt();
                        int _numDays = sc.nextInt();

                        // II. We create the object
                        Borrow myBorrow = new Borrow(_borrowID,
                                                     _userID,
                                                     _itemID,
                                                     _numDays
                                                    );

                        // III. We append the object to the list
                        myUser.getActiveBorrows().add(myBorrow);

                        // IV. If the id is bigger than the existing one, we update it
                        if (_borrowID >= LibraryImp.nextId)
                            LibraryImp.nextId = _borrowID + 1;
                    }

                    // 2.2.7. We append the object to the list
                    this.usersList.add(myUser);
                }
            }

            //3. We close the scanner
            sc.close();

            // 4. We set res to true
            res = true;
        }
        catch (Exception e) {
            System.out.println("Sorry but there is not such file");
        }

        // We return res
        return res;
    }


    //---------------------------------------
    //	loadItemsFromDisk
    //---------------------------------------
    /**
     * This method attempts to load the items from disk.<br>
     * @param itemsFileName - The name of the database file for items.
     */
    private boolean loadItemsFromDisk(String itemsFileName){
        // 1. We create the output variable
        boolean res = false;

        try {
            // 1. We create the file variable
            File my_file = new File(itemsFileName);
            Scanner sc = new Scanner(my_file);

            // 2. If the file is not empty
            if (sc.hasNext()){
                // 2.1. We get the number of users
                int itemsSize = sc.nextInt();

                // 2.2. We parse each of the user objects
                for (int itemIndex = 0; itemIndex < itemsSize; itemIndex++){
                    // 2.2.1. We read the values for the object
                    int _id = sc.nextInt();
                    String _title = sc.next();
                    String _author = sc.next();
                    int _releaseYear = sc.nextInt();
                    String _genre = sc.next();
                    int _numPages;
                    boolean _isAlbumOfYear;

                    // 2.2.2. We get the type of object based on the last parameter
                    Item myItem;
                    if (sc.hasNextInt()){
                        // I. We read the number of pages
                        _numPages = sc.nextInt();

                        // II. We create the object
                        myItem = new Book(_id,
                                          _title,
                                          _author,
                                          _releaseYear,
                                          _genre,
                                          _numPages
                                         );
                    }
                    else{
                        // I. We read the number of pages
                        _isAlbumOfYear = sc.nextBoolean();

                        // II. We create the object
                        myItem = new MusicAlbum(_id,
                                                _title,
                                                _author,
                                                _releaseYear,
                                                _genre,
                                                _isAlbumOfYear
                                               );
                    }

                    // 2.2.3. If the id is bigger than the existing one, we update it
                    if (_id >= LibraryImp.nextId)
                        LibraryImp.nextId = _id + 1;

                    // 2.2.4. We find the info of the borrowing

                    if (sc.hasNextInt()){
                        // I. We read the values for the object
                        sc.nextInt();
                        int _userID = sc.nextInt();
                        int _itemID = sc.nextInt();
                        sc.nextInt();

                        // II. We look for the index of userID
                        int userIndex = User.isUserInUsersList(_userID, this.usersList);
                        User myUser = this.usersList.get(userIndex);

                        // III. We look for the index of borrowID
                        ArrayList<Borrow> borrowList = myUser.getActiveBorrows();
                        int borrowIndex = Borrow.isItemInBorrowsList(_itemID, borrowList);

                        // IV. We get the borrow object
                        Borrow myBorrow = borrowList.get(borrowIndex);

                        // V. We associate the borrow object to the item
                        myItem.setCurrentBorrowed(myBorrow);
                    }
                    else{
                        // I. If the borrowing was null, we just consume the item
                        sc.next();
                    }

                    // 2.2.5. We add the item to the list
                    this.itemsList.add(myItem);
                }
            }

            //3. We close the scanner
            sc.close();

            // 4. We set res to true
            res = true;
        }
        catch (Exception e) {
            System.out.println("Sorry but there is not such file");
        }

        // We return res
        return res;
    }

    //---------------------------------------
    //	saveContentToDisk
    //---------------------------------------
    /**
     * This method attempts to save the users, items and borrows of the library to disk.<br>
     * @param usersFileName - The name of the database file for users.
     * @param itemsFileName - The name of the database file for items.
     * @return Whether the content is saved or not.
     */
    public boolean saveContentToDisk(String usersFileName,
                                     String itemsFileName
                                    ) {

        // 1. We create the output variable
        boolean res = false;

        // 2. We create the print writers objects
        PrintWriter usersWriter;
        PrintWriter itemsWriter;

        // 3. We try to save the content
        try {
            //3.1. We open the files for writing
            usersWriter = new PrintWriter(usersFileName, StandardCharsets.UTF_8);
            itemsWriter = new PrintWriter(itemsFileName, StandardCharsets.UTF_8);

            //3.2. We print the info from the users
            int size = this.usersList.size();
            usersWriter.println(size);
            for (int index = 0; index < size; index++){
                User myUser = this.usersList.get(index);
                String content = myUser.toString();
                usersWriter.print(content);
            }

            //3.3. We print the info from the items
            size = this.itemsList.size();
            itemsWriter.println(size);
            for (int index = 0; index < size; index++){
                Item myItem = this.itemsList.get(index);
                String content = myItem.toString();
                itemsWriter.print(content);
            }

            //3.4. We close the files
            usersWriter.close();
            itemsWriter.close();

            // 3.5. We mark the operation as completed
            res = true;
        }

        // 4. We capture any exception happening
        catch (Exception e) {
            System.out.println("Sorry but the file cannot be created for writing to it");
        }

        // 5. We return res
        return res;
    }


}

