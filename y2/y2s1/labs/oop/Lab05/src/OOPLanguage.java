/**
 * OOPLanguage
 */
public class OOPLanguage extends ProgrammingLanguage {

    private boolean isCompiled;

    // Constructors

    public OOPLanguage(
        int _numWorldSpeakers,
        int _learningMonths,
        boolean _isCompiled
    ) {
        super(_learningMonths, _numWorldSpeakers);
        this.isCompiled = _isCompiled;
    }

    // Getters

    public boolean getIsCompiled() {
        return this.isCompiled;
    }

    // Setters

    public void setIsCompiled(boolean compiled_) {
        this.isCompiled = compiled_;
    }

    // Extra functionality

    public int computeSalary() {
        int salary;
        if (this.isCompiled == false) {
            salary = 50000;
        } else {
            salary = super.computeSalary();
        }
        return salary;
    }
}
