
/**
 * The class tests the functionality of A02_Part1.
 * @author Ignacio.Castineiras
 *
 */
public class MyMain {

	//--------------------------------------------------
	//	testClassStudent
	//--------------------------------------------------
	/**
	 * This function tests the functionality of the class Student.<br>
	 */
	public static void testClassStudent(){
		System.out.println("\n------------------------------\n\n   TESTING CLASS STUDENT\n\n------------------------------\n");

		//1. We create some Module objects.
		System.out.println("\n----------- 1. Module instances creation -----------");

		//1.1. A first Student, with a constuctor using as arguments "Kellie Harrington", 150123 and true (for its Programming Society membership).
		Student o1 = new Student("Kellie Harrington", 150123, true);

		//1.2. A second Student, with a constuctor using as arguments "Philip Doyle", 400123 and false (for its Programming Society membership).
		Student o2 = new Student("Philip Doyle", 400123, false);

		//2. We use our objects to call to the getMethods.
		System.out.println("\n----------- 2. Student test for getMethods -----------");

		//2.1. Check the fields of o1.
		System.out.println("\n--- The value of the field 'name' in o1 is = ");
		String myTitle = o1.getName();
		System.out.println(myTitle);

		System.out.println("\n--- The value of the field 'id' in o1 is = ");
		int myCRN = o1.getId();
		System.out.println(myCRN);

		System.out.println("\n--- The value of the field 'memberOfProgSociety' in o1 is = ");
		boolean myIsCA = o1.getMemberOfProgSociety();
		System.out.println(myIsCA);

		//2.2. Check the fields of o2.
		System.out.println("\n--- The value of the field 'name' in o2 is = ");
		myTitle = o2.getName();
		System.out.println(myTitle);

		System.out.println("\n--- The value of the field 'id' in o2 is = ");
		myCRN = o2.getId();
		System.out.println(myCRN);

		System.out.println("\n--- The value of the field 'memberOfProgSociety' in o2 is = ");
		myIsCA = o2.getMemberOfProgSociety();
		System.out.println(myIsCA);

		//3. We use our objects to call to the setMethods.
		System.out.println("\n----------- 3. Student test for setMethods -----------");

		// 3.1. We modify the values of o2
		o1.setName("Daire Lynch");
		o1.setId(500123);
		o1.setMemberOfProgSociety(false);

		System.out.println("\n--- The value of the field 'name' in o1 is = ");
		myTitle = o1.getName();
		System.out.println(myTitle);

		System.out.println("\n--- The value of the field 'id' in o1 is = ");
		myCRN = o1.getId();
		System.out.println(myCRN);

		System.out.println("\n--- The value of the field 'memberOfProgSociety' in o1 is = ");
		myIsCA = o1.getMemberOfProgSociety();
		System.out.println(myIsCA);

		//4. We use our objects to call to the extra functionality.
		System.out.println("\n----------- 4. Student test extra methods -----------");

		// 4.1. We test the method increaseBoldnessLevel
		System.out.println("\n--- Testing printStudentInfo");
		o1.printStudentInfo(1);
		o1.printStudentInfo(2);
		o1.printStudentInfo(3);
		o1.printStudentInfo(4);

		o1.setName("Kellie Harrington");
		o1.setId(150123);
		o1.setMemberOfProgSociety(true);

		o1.printStudentInfo(1);
		o1.printStudentInfo(2);
		o1.printStudentInfo(3);
		o1.printStudentInfo(4);

		System.out.println("\n--- Testing sumOfIDDigits");
		int value = o1.sumOfIDDigits();
		System.out.println(value);
		value = o2.sumOfIDDigits();
		System.out.println(value);
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
		// 1. We parse any parameter in


		// 2. We call to the method we are interested into
		testClassStudent();
	}

}
