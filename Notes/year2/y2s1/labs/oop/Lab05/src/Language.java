/**
 * Language
 */
import java.lang.Math;

public class Language extends Object {

    // Fields
    protected boolean isAlphabetical;
    protected int speakers;

    // Constructors

    public Language() {

		this.isAlphabetical = false;
		this.speakers = 0;

	}

    public Language(boolean alphabeticalLanguage_, int numSpeakers) {
        this.isAlphabetical = alphabeticalLanguage_;
        this.speakers = numSpeakers;
    }

    // Getters

    public boolean getAlphabetBased() {
        return this.isAlphabetical;
    }

    public int getNumWorldSpeakers() {
        return this.speakers;
    }

    // Setters

    public void setAlphabetBased(boolean newAlphaStatus) {
        this.isAlphabetical = newAlphaStatus;
    }

    public void setNumWorldSpeakers(int newSpeakersNum) {
        this.speakers = newSpeakersNum;
    }

    // Extra functionality

    public int computeSalary() {
        int salary;
        if (this.isAlphabetical == false) {
            salary = 40000;
        } else {
            salary = 60000 - (1000 * (Math.floorDiv(this.speakers, 1000000)));
            if (salary <= 30000) {
                salary = 30000;
            }
        }
        return salary;
    }

    public final int computeSalary(int experienceYears) {
        return experienceYears * 5000;
    }
}
