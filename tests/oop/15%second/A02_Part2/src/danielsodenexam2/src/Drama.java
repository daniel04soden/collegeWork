
public class Drama extends Movie {

  // ---------------------------------------
  // FIELD
  // ---------------------------------------
  public int numFamousActors;

  // ---------------------------------------
  // CONSTRUCTOR
  // ---------------------------------------
  public Drama(String _title, int _numFamousActors) {
    super(_title);
    this.numFamousActors = _numFamousActors;
  }

  // ---------------------------------------
  // GET METHOD
  // ---------------------------------------
  public int getNumFamousActors() {
    return this.numFamousActors;
  }

  // ---------------------------------------
  // SET METHOD
  // ---------------------------------------
  public void setNumFamousActors(int _numFamousActors) {
    this.numFamousActors = _numFamousActors;
  }
  // ---------------------------------------
  // EXTRA METHOD
  // ---------------------------------------

  public int revenue() {
    int res;

    final int titleBoost = 1000000; // Defining constants for better readability of returned values
    final int famousActBoost = 5000000;

    int titleValue = this.getTitle().length() * titleBoost;
    res = titleValue + (famousActBoost * this.numFamousActors);

    return res;
  }

}
