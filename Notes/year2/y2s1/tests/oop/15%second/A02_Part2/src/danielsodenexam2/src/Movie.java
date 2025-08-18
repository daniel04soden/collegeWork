
public abstract class Movie {

  // ---------------------------------------
  // FIELD
  // ---------------------------------------
  private String title; // A string describing the title of the movie

  // ---------------------------------------
  // CONSTRUCTOR
  // ---------------------------------------
  public Movie(String _title) {
    this.title = _title; // The constructed title is now this objects title
  }

  // ---------------------------------------
  // GET METHOD
  // ---------------------------------------
  public String getTitle() {
    return this.title; // Return this objects title
  }

  // ---------------------------------------
  // SET METHOD
  // ---------------------------------------
  public void setTitle(String _title) {
    this.title = _title; // Setting field title to given title
  }

  // ---------------------------------------
  // EXTRA METHOD
  // ---------------------------------------
  public abstract int revenue(); // Abstract method does not contain a body
}
