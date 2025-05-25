/**
 * CompanySoftwareProject
 */
public class CompanySoftwareProject extends Object {

    // Fields
    private int numProgrammers;
    private ProgrammingLanguage myProgrammingLanguage;

    // Constructors
    public CompanySoftwareProject(
        int _numProgrammers,
        ProgrammingLanguage _myProgrammingLanguage
    ) {
        this.numProgrammers = _numProgrammers;
        this.myProgrammingLanguage = _myProgrammingLanguage;
    }

    // Getters and setters

    public int getNumProgrammers() {
        return numProgrammers;
    }

    public void setNumProgrammers(int numProgrammers) {
        this.numProgrammers = numProgrammers;
    }

    public ProgrammingLanguage getMyProgrammingLanguage() {
        return myProgrammingLanguage;
    }

    public void setMyProgrammingLanguage(
        ProgrammingLanguage myProgrammingLanguage
    ) {
        this.myProgrammingLanguage = myProgrammingLanguage;
    }

    // Extra functionality

    public int computeProjectCost() {
        return this.numProgrammers * this.myProgrammingLanguage.computeSalary();
    }
}
