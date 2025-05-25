
/**
 * The class tests the functionality of Lab05.
 * @author Ignacio.Castineiras
 *
 */
public class MyMain {

	//--------------------------------------------------
	//	testClassLanguage
	//--------------------------------------------------
	/**
	 * This function tests the functionality of the class Language.<br>
	 */
	public static void testClassLanguage(){
		Language l1 = new Language();

		boolean b = l1.getAlphabetBased();
		System.out.println(b);
		int i = l1.getNumWorldSpeakers();
		System.out.println(i);

		l1.setAlphabetBased(true);
		l1.setNumWorldSpeakers(50);

		b = l1.getAlphabetBased();
		System.out.println(b);
		i = l1.getNumWorldSpeakers();
		System.out.println(i);

		i = l1.computeSalary();
		System.out.println(i);

		l1.setNumWorldSpeakers(10);
		i = l1.computeSalary();
		System.out.println(i);

		l1.setAlphabetBased(false);
		i = l1.computeSalary();
		System.out.println(i);

		i = l1.computeSalary(20);
		System.out.println(i);

		Language l2 = new Language(true, 10);
		i = l2.computeSalary();
		System.out.println(i);
	}


	//--------------------------------------------------
	//	testClassProgrammingLanguage
	//--------------------------------------------------
	/**
	 * This function tests the functionality of the class ProgrammingLanguage.<br>
	 */
	public static void testClassProgrammingLanguage(){
		ProgrammingLanguage l1 = new ProgrammingLanguage(10, 18);

		boolean b = l1.getAlphabetBased();
		System.out.println(b);
		int i = l1.getNumWorldSpeakers();
		System.out.println(i);
		i = l1.getLearningMonths();
		System.out.println(i);

		l1.setAlphabetBased(false);
		l1.setNumWorldSpeakers(50);

		b = l1.getAlphabetBased();
		System.out.println(b);
		i = l1.getNumWorldSpeakers();
		System.out.println(i);
		i = l1.getLearningMonths();
		System.out.println(i);

		i = l1.computeSalary();
		System.out.println(i);

		l1.setNumWorldSpeakers(5);
		i = l1.computeSalary();
		System.out.println(i);

		l1.setAlphabetBased(false);
		i = l1.computeSalary();
		System.out.println(i);

		i = l1.computeSalary(20);
		System.out.println(i);

		ProgrammingLanguage l2 = new ProgrammingLanguage( 18);

		b = l2.getAlphabetBased();
		System.out.println(b);
		i = l2.getNumWorldSpeakers();
		System.out.println(i);
		i = l2.getLearningMonths();
		System.out.println(i);

		l2.setAlphabetBased(false);
		l2.setNumWorldSpeakers(50);

		b = l2.getAlphabetBased();
		System.out.println(b);
		i = l2.getNumWorldSpeakers();
		System.out.println(i);
		i = l2.getLearningMonths();
		System.out.println(i);
	}


	//--------------------------------------------------
	//	testClassOOPLanguage
	//--------------------------------------------------
	/**
	 * This function tests the functionality of the class OOPLanguage.<br>
	 */
	public static void testClassOOPLanguage(){
		OOPLanguage l1 = new OOPLanguage(10, 18, false);

		boolean b = l1.getAlphabetBased();
		System.out.println(b);
		int i = l1.getNumWorldSpeakers();
		System.out.println(i);
		i = l1.getLearningMonths();
		System.out.println(i);
		b = l1.getIsCompiled();
		System.out.println(b);

		l1.setAlphabetBased(false);
		l1.setNumWorldSpeakers(50);

		b = l1.getAlphabetBased();
		System.out.println(b);
		i = l1.getNumWorldSpeakers();
		System.out.println(i);
		i = l1.getLearningMonths();
		System.out.println(i);

		i = l1.computeSalary();
		System.out.println(i);

		l1.setIsCompiled(true);
		i = l1.computeSalary();
		System.out.println(i);

		l1.setAlphabetBased(false);
		i = l1.computeSalary();
		System.out.println(i);

		i = l1.computeSalary(20);
		System.out.println(i);
	}


	//--------------------------------------------------
	//	CompanySoftwareProject
	//--------------------------------------------------
	/**
	 * This function tests the functionality of the class CompanySoftwareProject.<br>
	 */
	public static void testClassComputerSoftwareProject(){
		OOPLanguage l1 = new OOPLanguage(10, 18, false);

		CompanySoftwareProject c1 = new CompanySoftwareProject(5, l1);

		int i = c1.getNumProgrammers();
		System.out.println(i);
		i = c1.computeProjectCost();
		System.out.println(i);

		ProgrammingLanguage l2 = new ProgrammingLanguage( 10);
		c1.setMyProgrammingLanguage(l2);

		i = c1.getNumProgrammers();
		System.out.println(i);
		i = c1.computeProjectCost();
		System.out.println(i);
	}



	//--------------------------------------------------
	//	MAIN
	//--------------------------------------------------
	/**
	 * Main Method:<br>
	 * 1) It calls to drinkFunctionality.<br>
	 * The function shows the main class and object-related concepts we must know for the module.<br>
	 * 2) It calls to refVarsFunctionality.<br>
	 * The function shows the main primitive vs. reference variables concepts we must know for the module.<br>
	 * @param args: We will run the program parameter free, so do not worry about it.
	 */
	public static void main(String[] args) {
		Drink coffeeDrink = new Drink();
		// 1. We parse any parameter in
		int option = 4;

		// 2. We call to the method we want to try
		switch (option) {
			case 1:
				testClassLanguage();
				System.out.println(coffeeDrink.getIsHot());
			System.out.println(coffeeDrink.getSugarCount());
				break;
			case 2:
				testClassProgrammingLanguage();
				break;
			case 3:
				testClassOOPLanguage();
				break;
			case 4:
			
				testClassComputerSoftwareProject();
				break;
				
		}
	}

}