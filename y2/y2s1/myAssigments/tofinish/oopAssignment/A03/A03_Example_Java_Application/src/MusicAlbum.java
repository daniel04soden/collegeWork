//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------


//--------------------------------------------------
//
//	CLASS Item
//
//--------------------------------------------------
/**
 * This class models a music album item of the library<br>.
 */
public class MusicAlbum extends Item {


    //---------------------------------------
    //	Fields
    //---------------------------------------
    private final boolean isAlbumOfYear;


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
     * @param _isAlbumOfYear - Whether the album was named album of the year when released.
     */
    public MusicAlbum(int _id,
                      String _title,
                      String _author,
                      int _releaseYear,
                      String _genre,
                      boolean _isAlbumOfYear
                     ){

        // 1. We call to the item constructor, to initialise all fields except the one specific to books.
        super(_id,
              _title,
              _author,
              _releaseYear,
              _genre
             );

        // 2. We initialise the remaining fields
        this.isAlbumOfYear = _isAlbumOfYear;
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
    public int numDaysToBorrow(){ return (this.isAlbumOfYear) ? 7 : 3; }


    //---------------------------------------
    //	toString
    //---------------------------------------
    /**
     * Given a concrete music album (this), the function overrides the method toString.<br>
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

        // 3. We print the music album specific field
        res = res + this.isAlbumOfYear + "\n";

        // 4. We append the info of the borrow
        if (this.getCurrentBorrowed() == null)
            res = res + "null\n";
        else
            res = res + this.getCurrentBorrowed().toString();

        // 5. We return res
        return res;
    }


}
