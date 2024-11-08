
public class ProgrammingLanguage extends Language {
  protected int learningMonths;

  public ProgrammingLanguage(int _learningMonths) {
    super();
    this.learningMonths = _learningMonths;
    this.setAlphabetBased(true);
  }

  public ProgrammingLanguage(int _numWorldSpeakers, int _learningMonths) {
    super(true, _numWorldSpeakers);
    this.learningMonths = _learningMonths;
  }

  public int getLearningMonths() {
    return this.learningMonths;
  }

  public void setLearningMonths(int _newLearningMonths) {
    this.learningMonths = _newLearningMonths;
  }

  public void setAlphabetBased(boolean _alphabetBased) {
    super.setAlphabetBased(_alphabetBased);

  }

  // Extra functionality
  public int computeSalary() {
    return super.computeSalary() + (1000 * this.learningMonths);
  }
}
