import java.util.Scanner;
/**
 * The class tests the functionality of Lab02.
 * @author Ignacio.Castineiras
 *
 */
public class MyMain {

	//--------------------------------------------------
	//	testClassDog
	//--------------------------------------------------
	/**
	 * This function tests the functionality of the class Dog.<br>
	 */
	public static void testClassDog(){
		System.out.println("\n------------------------------\n\n   TESTING CLASS DOG\n\n------------------------------\n");

		//1. We create some Dog objects.
		System.out.println("\n----------- 1. Dog instances creation -----------");

		//1.1. A first Dog, with name Ron, 7 in boldness level and breed Greyhound
		Dog o1 = new Dog("Ron", 17, "Greyhound");

		//1.2. A second Dog, with name Jessy, 5 in boldness level and breed Golden Retriever
		Dog o2 = new Dog("Jessy", 5, "Golden Retriever");

		//2. We use our objects to call to the getMethods.
		System.out.println("\n----------- 2. Dog test for getMethods -----------");

		//2.1. Check the fields of o1.
		System.out.println("\n--- The value of the field 'name' in o1 is = ");
		String myName = o1.getName();
		System.out.println(myName);

		System.out.println("\n--- The value of the field 'boldnessLevel' in o1 is = ");
		int myBoldnessLevel = o1.getBoldnessLevel();
		System.out.println(myBoldnessLevel);

		System.out.println("\n--- The value of the field 'breed' in o1 is = ");
		String myBreed = o1.getBreed();
		System.out.println(myBreed);

		//2.2. Check the fields of o2.
		System.out.println("\n--- The value of the field 'name' in o2 is = ");
		myName = o2.getName();
		System.out.println(myName);

		System.out.println("\n--- The value of the field 'boldnessLevel' in o2 is = ");
		myBoldnessLevel = o2.getBoldnessLevel();
		System.out.println(myBoldnessLevel);

		System.out.println("\n--- The value of the field 'breed' in o2 is = ");
		myBreed = o2.getBreed();
		System.out.println(myBreed);

		//3. We use our objects to call to the setMethods.
		System.out.println("\n----------- 3. Dog test for setMethods -----------");

		// 3.1. We modify the values of o1
		o1.setName("Ronchi");
		o1.setBoldnessLevel(9);
		o1.setBreed("Podenco");

		System.out.println("\n--- The value of the field 'name' in o1 is = ");
		myName = o1.getName();
		System.out.println(myName);

		System.out.println("\n--- The value of the field 'boldnessLevel' in o1 is = ");
		myBoldnessLevel = o1.getBoldnessLevel();
		System.out.println(myBoldnessLevel);

		System.out.println("\n--- The value of the field 'breed' in o1 is = ");
		myBreed = o1.getBreed();
		System.out.println(myBreed);

		//4. We use our objects to call to the extra functionality.
		System.out.println("\n----------- 4. Dog test extra methods -----------");

		// 4.1. We test the method increaseBoldnessLevel
		System.out.println("\n--- The value of the field 'boldnessLevel' in o2 is = ");
		myBoldnessLevel = o2.getBoldnessLevel();
		System.out.println(myBoldnessLevel);

		o2.increaseBoldnessLevel();
		System.out.println("\n--- The value of the field 'boldnessLevel' in o2 is = ");
		myBoldnessLevel = o2.getBoldnessLevel();
		System.out.println(myBoldnessLevel);

		// 4.2. We test the method bark
		System.out.println("\n--- The way o2 barks in this situation is = ");
		o2.bark(1);

		System.out.println("\n--- The way o2 barks in this situation is = ");
		o2.bark(4);
	}

	//--------------------------------------------------
	//	testClassCat
	//--------------------------------------------------
	/**
	 * This function tests the functionality of the class Cat.<br>
	 */
	public static void testClassCat(){
		System.out.println("\n------------------------------\n\n   TESTING CLASS CAT\n\n------------------------------\n");

		//1. We create some Cat objects.
		System.out.println("\n----------- 1. Cat instances creation -----------");

		//1.1. A first Cat, with name Mocha and 6 in playful level.
		Cat o1 = new Cat("Mocha", 6);

		//1.2. A first Cat, with name Latte and 5 in playful level.
		Cat o2 = new Cat("Latte", 5);

		//2. We use our objects to call to the getMethods.
		System.out.println("\n----------- 2. Cat test for getMethods -----------");

		//2.1. Check the fields of o1.
		System.out.println("\n--- The value of the field 'name' in o1 is = ");
		String myName = o1.getName();
		System.out.println(myName);

		System.out.println("\n--- The value of the field 'playfulLevel' in o1 is = ");
		int myPlayfulLevel = o1.getPlayfulLevel();
		System.out.println(myPlayfulLevel);

		//2.2. Check the fields of o2.
		System.out.println("\n--- The value of the field 'name' in o2 is = ");
		myName = o2.getName();
		System.out.println(myName);

		System.out.println("\n--- The value of the field 'playfulLevel' in o2 is = ");
		myPlayfulLevel = o2.getPlayfulLevel();
		System.out.println(myPlayfulLevel);

		//3. We use our objects to call to the setMethods.
		System.out.println("\n----------- 3. Cat test for setMethods -----------");

		// 3.1. We modify the values of o1
		o1.setName("Moki");
		o1.setPlayfulLevel(8);

		System.out.println("\n--- The value of the field 'name' in o1 is = ");
		myName = o1.getName();
		System.out.println(myName);

		System.out.println("\n--- The value of the field 'playfulLevel' in o1 is = ");
		myPlayfulLevel = o1.getPlayfulLevel();
		System.out.println(myPlayfulLevel);

		//4. We use our objects to call to the extra functionality.
		System.out.println("\n----------- 4. Cat test extra methods -----------");

		// 4.1. We test the method payAttention
		boolean attention = false;
		for (int i = 0; i < 10; i++){
			attention = o1.isPayingAttention();
			System.out.println("\n--- Is o1 paying attention at the moment = ");
			System.out.println(attention);
		}

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
    Scanner scanning = new Scanner(System.in);
    System.out.println("Would you like to test: \n 1)Dog\n 2)Cat");
		int option = scanning.nextInt();


		// 2. We call to the method we are interested into
		switch (option) {
			case 1:
				testClassDog();
				break;
			case 2:
				testClassCat();
				break;
		}
	}

}
