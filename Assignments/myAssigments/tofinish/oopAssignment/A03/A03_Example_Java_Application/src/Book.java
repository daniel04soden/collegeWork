//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------


//--------------------------------------------------
//
//	CLASS Item
//
//--------------------------------------------------
/**
 * This class models a book item of the library<br>.
 */
public class Book extends Item {


    //---------------------------------------
    //	Fields
    //---------------------------------------
    private final int numPages;


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
     * @param _numPages - The number of pages of the book.
     */
    public Book(int _id,
                String _title,
                String _author,
                int _releaseYear,
                String _genre,
                int _numPages
               ){

        // 1. We call to the item constructor, to initialise all fields except the one specific to books.
        super(_id,
              _title,
              _author,
              _releaseYear,
              _genre
             );

        // 2. We initialise the remaining fields
        this.numPages = _numPages;
    }


    //---------------------------------------
    //	GET METHODS
    //---------------------------------------


    //---------------------------------------
    //	getNumPages
    //---------------------------------------
    /**
     * Given a concrete book (this), the function returns its number of pages.<br>
     * @return The number of pages of the book.
     */
    public int getNumPages(){
        return this.numPages;
    }


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
    public int numDaysToBorrow(){ return this.numPages / 100; }


    //---------------------------------------
    //	toString
    //---------------------------------------
    /**
     * Given a concrete book (this), the function overrides the method toString.<br>
     */
    public String toString(){
        // 1. We create the output variable
        String res;

        // 2. We append the user info
        res = "" + this.getId() + "\t" +
                this.getTitle() + "\t" +
                this.getAuthor() + "\t" +
                this.getReleaseYear() + "\t" +
                this.getGenre() + "\t";

        // 3. We print the book specific field
        res = res + this.getNumPages() + "\n";

        // 4. We append the info of the borrow
        if (this.getCurrentBorrowed() == null)
            res = res + "null\n";
        else
            res = res + this.getCurrentBorrowed().toString();

        // 5. We return res
        return res;
    }

}
