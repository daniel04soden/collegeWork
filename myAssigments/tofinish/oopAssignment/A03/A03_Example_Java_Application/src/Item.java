//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.ArrayList;

//--------------------------------------------------
//
//	ABSTRACT CLASS Item
//
//--------------------------------------------------
/**
 * This abstract class models an item of the library<br>.
 */
public abstract class Item extends Agent {

    //---------------------------------------
    //	Fields
    //---------------------------------------
    private final String title;
    private final String author;
    private final int releaseYear;
    private final String genre;
    private Borrow currentBorrowed;


    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * The constructor creates 1 instance (1 object) of the class Item<br>
     * @param _id - The ID of the item.
     * @param _title - The title of the item.
     * @param _author - The author of the item.
     * @param _releaseYear - The year the item was release.
     * @param _genre - The genre of the item.
     */
    public Item(int _id,
                String _title,
                String _author,
                int _releaseYear,
                String _genre
               ){

        // 1. We call to the agent constructor, to initialise the id and the borrowHistory
        super(_id);

        // 2. We initialise the remaining fields
        this.title = _title;
        this.author = _author;
        this.releaseYear = _releaseYear;
        this.genre = _genre;

        // 3. The item is not borrowed
        this.currentBorrowed = null;
    }


    //---------------------------------------
    //	GET METHODS
    //---------------------------------------


    //---------------------------------------
    //	getTitle
    //---------------------------------------
    /**
     * Given a concrete item (this), the function returns its title.<br>
     * @return The title of the item.
     */
    public String getTitle(){
        return this.title;
    }


    //---------------------------------------
    //	getAuthor
    //---------------------------------------
    /**
     * Given a concrete item (this), the function returns its author.<br>
     * @return The author of the item.
     */
    public String getAuthor(){
        return this.author;
    }


    //---------------------------------------
    //	getReleaseYear
    //---------------------------------------
    /**
     * Given a concrete item (this), the function returns its release year.<br>
     * @return The release year of the item.
     */
    public int getReleaseYear(){
        return this.releaseYear;
    }


    //---------------------------------------
    //	getGenre
    //---------------------------------------
    /**
     * Given a concrete item (this), the function returns its genre.<br>
     * @return The genre of the item.
     */
    public String getGenre(){
        return this.genre;
    }


    //---------------------------------------
    //	getCurrentBorrowed
    //---------------------------------------
    /**
     * Given a concrete item (this), the function returns the borrow object it is associated to (null if not borrowed).<br>
     * @return The borrow object it is associated to (null if not borrowed).
     */
    public Borrow getCurrentBorrowed(){ return this.currentBorrowed; }


    //---------------------------------------
    //	SET METHODS
    //---------------------------------------


    //---------------------------------------
    //	setCurrentBorrowed
    //---------------------------------------
    /**
     * Given a concrete item (this), the function updates its borrow object.<br>
     * @param _newBorrow - The new borrow object to be associated to the item.
     */
    public void setCurrentBorrowed(Borrow _newBorrow){ this.currentBorrowed = _newBorrow ; }


    //---------------------------------------
    //	EXTRA METHODS
    //---------------------------------------


    //---------------------------------------
    //	numDaysToBorrow
    //---------------------------------------
    /**
     * Given a concrete item (this), the function returns the amount of borrow days.<br>
     * @return The amount of borrow days.
     */
    public abstract int numDaysToBorrow();


    //---------------------------------------
    //	isItemInItemsList
    //---------------------------------------
    /**
     * This method attempts to find it an item is in the itemsList<br>
     * @param id - The id of the item.
     * @param myList - The list of items where we are looking for.
     * @return The index in myList where the agent with id is located at; (-1) if it is not in the list.
     */
    public static int isItemInItemsList(int id, ArrayList<Item> myList){
        // 1. We create the output variable
        int res = -1;

        // 2. We create the agent from the id
        Agent baseline = new Agent(id);

        // 2. We search for the agent in the list
        int size = myList.size();
        int index = 0;

        // 3. If we found the itemID in the list, we assign its index to res
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
