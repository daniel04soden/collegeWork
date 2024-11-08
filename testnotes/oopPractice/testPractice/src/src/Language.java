public class Language {
  private boolean alphabetBased;
  private int numWorldSpeakers;

  public Language() {
    this.alphabetBased = false;
    this.numWorldSpeakers = 0;
  }

  public Language(boolean _alphabetBased, int _numWorldSpeakers) {
    this.alphabetBased = _alphabetBased;
    this.numWorldSpeakers = _numWorldSpeakers;
  }

  // getters and setters

  public boolean isAlphabetBased() {
    return alphabetBased;
  }

  public void setAlphabetBased(boolean alphabetBased) {
    this.alphabetBased = alphabetBased;
  }

  public int getNumWorldSpeakers() {
    return numWorldSpeakers;
  }

  public void setNumWorldSpeakers(int numWorldSpeakers) {
    this.numWorldSpeakers = numWorldSpeakers;
  }

  // Extra functionality

  public int computeSalary() {
    int res = 30000;
    if (!alphabetBased) {
      res = 40000;
    } else {
      res = 60000 - (1000 * (numWorldSpeakers / 1000000));
    }
    if (res < 30000) {
      return 30000;
    } else {
      return res;
    }
  }

  public final int computeSalary(int _numYearsExperience) {
    int res = 5000 * _numYearsExperience;
    return res;
  }
}
