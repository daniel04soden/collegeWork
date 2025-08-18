
public class PeriodDrama extends Drama {
  // ---------------------------------------
  // FIELD
  // ---------------------------------------
  private int numIndoorSets;

  // ---------------------------------------
  // CONSTRUCTOR
  // ---------------------------------------

  public PeriodDrama(String _title, int _numFamousActors, int _numIndoorSets) {
    super(_title, _numFamousActors);
    this.numIndoorSets = _numIndoorSets;
  }

  // ---------------------------------------
  // GET METHOD
  // ---------------------------------------
  public int getNumIndoorSets() {
    return this.numIndoorSets;
  }

  // ---------------------------------------
  // SET METHOD
  // ---------------------------------------
  public void setNumIndoorSets(int _numIndoorSets) {
    this.numIndoorSets = _numIndoorSets;
  }

  // ---------------------------------------
  // EXTRA METHODS
  // ---------------------------------------
  public int revenue() {
    int res;
    final int numIndoorSetsBoost = 1000000;

    res = this.numIndoorSets * numIndoorSetsBoost;

    return res;
  }

  public int revenue(boolean b) {
    int res;
    if (b) {
      res = this.revenue();
    } else {
      res = super.revenue();
    }
    return res;
  }
}
