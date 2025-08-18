public class OOPLanguage extends ProgrammingLanguage {
  protected boolean isCompiled;

  public OOPLanguage(int _numWorldSpeakers, int _learningMonths, boolean _isCompiled) {
    super(_learningMonths);
    this.isCompiled = _isCompiled;
    super.setNumWorldSpeakers(_numWorldSpeakers);
  }

  // Getters

  public boolean getIsCompiled() {
    return this.isCompiled;
  }

  public void setIsCompiled(boolean isCompiled_) {
    this.isCompiled = isCompiled_;
  }

  public int computeSalary() {
    if (!isCompiled) {
      return 50000;
    } else {
      return super.computeSalary();
    }
  }
}
