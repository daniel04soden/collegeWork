/**
 * ProgrammingLanguage
 */
public class ProgrammingLanguage extends Language {

    // Fields
    protected int learningMonths;

    // Constructors
    public ProgrammingLanguage(int monthsLearned) {
				super();
        this.learningMonths = monthsLearned;
				this.setAlphabetBased(true);
    }

    public ProgrammingLanguage(int _numWorldSpeakers, int _monthsLearned) {
        super(true, _numWorldSpeakers);
        this.learningMonths = _monthsLearned;
    }

    // Getters

    public int getLearningMonths() {
        return learningMonths;
    }

    // Setter

    public void setLearningMonths(int learningMonths) {
        this.learningMonths = learningMonths;
    }

    public void setAlphabetBased(boolean _alphabetBased) {
        super.setAlphabetBased(_alphabetBased);
    }

    // Extra Functionality

    public int computeSalary() {
        int salary = super.computeSalary();

        for (int i = 0; i < this.learningMonths; i++) {
            salary += 1000;
        }
        return salary;
    }
}
