
/**
 * The class tests the functionality of Lab03.
 * @author Ignacio.Castineiras
 *
 */
public class MyMain {


	//--------------------------------------------------
	//	testConstructor1
	//--------------------------------------------------
	/**
	 * This function test the first Dog constructor.<br>
	 * In this constructor, a new object Toy must be created.
	 * Therefore, even if the toy of the dog shares the same field values (sound and resistance) as the toy t1,<br>
	 * the toy of the dog and t1 must be different toys.
	 */
	public static void testConstructor1(){
		System.out.println("\n------------------------------\n\n   TESTING FIRST CONSTRUCTOR OF DOG\n\n------------------------------\n");

		// 1. We create a Toy
		Toy t1 = new Toy(true, 3);

		// 2. We use the first constructor, the one that creates its own toy when creating the dog.
		Dog d1 = new Dog("Ron", 8, "Greyhound", true, 3);

		// 3. We check their fields
		String myName = d1.getName();
		System.out.println(myName);
		int myBoldnessLevel = d1.getBoldnessLevel();
		System.out.println(myBoldnessLevel);
		String myBreed = d1.getBreed();
		System.out.println(myBreed);

		// 4. We ensure that the two toys of the dogs are not the same
		Toy f = d1.getToy();
		if (f == t1){
			System.out.println("Same toy");
		}
		else{
			System.out.println("Different toy");
		}

		// 5. We get the toy field from d1
		Toy t2 = d1.getToy();

		// 6. We modify the toy resistance
		t2.setResistance(5);

		// 7. We check the resistance of t1 and t2
		int i = t1.getResistance();
		System.out.println(i);

		i = t2.getResistance();
		System.out.println(i);
	}


	//--------------------------------------------------
	//	testConstructor2
	//--------------------------------------------------
	/**
	 * This function test the second Dog constructor.<br>
	 * In this constructor, an existing object Toy is assigned to the Dog.<br>
	 * Therefore, the toy of the dog and t1 must be the very same toy.
	 */
	public static void testConstructor2(){
		System.out.println("\n------------------------------\n\n   TESTING SECOND CONSTRUCTOR OF DOG\n\n------------------------------\n");

		// 1. We create a Toy
		Toy t1 = new Toy(true, 3);

		// 2. We use the first constructor, the one that creates its own toy when creating the dog.
		Dog d1 = new Dog("Ron", 8, "Greyhound", t1);

		// 5. We check their fields
		String myName = d1.getName();
		System.out.println(myName);
		int myBoldnessLevel = d1.getBoldnessLevel();
		System.out.println(myBoldnessLevel);
		String myBreed = d1.getBreed();
		System.out.println(myBreed);

		// 6. We ensure that the two toys of the dogs are not the same
		Toy f = d1.getToy();
		if (f == t1){
			System.out.println("Same toy");
		}
		else{
			System.out.println("Different toy");
		}

		// 7. We get the toy field from d1
		Toy t2 = d1.getToy();

		// 8. We modify the toy resistance
		t2.setResistance(5);

		// 9. We check the resistance of t1 and t2
		int i = t1.getResistance();
		System.out.println(i);

		i = t2.getResistance();
		System.out.println(i);
	}


	//--------------------------------------------------
	//	testCopyConstructor
	//--------------------------------------------------
	/**
	 * This function test the third Dog constructor: the copy constructor.<br>
	 * - If the dog is copied using a shallow copy, the toy of both dogs must be the same.<br>
	 * - If the dog is copied using a deep copy, then the toy of the new dog must be a deep copy of the toy of the first dog.<br>
	 * @param mode - Whether the object is to be copied via shallow mode (true) or deep copy mode (false).
	 */
	public static void testCopyConstructor(boolean mode){
		System.out.print("\n------------------------------\n\n   TESTING COPY CONSTRUCTOR OF DOG IN ");
		if (mode == true)
			System.out.print("SHALLOW MODE");
		else
			System.out.print("DEEP MODE");
		System.out.println("\n\n------------------------------\n");

		// 1. We use the constructor using an already existing Toy
		Toy t1 = new Toy(true, 3);

		// 3. We create a first Dog using the toy previously created
		Dog d1 = new Dog("Ron", 8, "Greyhound", t1);

		// 4. We use the copy constructor in shallow mode
		Dog d2 = new Dog(d1, mode);

		// 5. We check their fields
		String myName = d1.getName();
		System.out.println(myName);
		int myBoldnessLevel = d1.getBoldnessLevel();
		System.out.println(myBoldnessLevel);
		String myBreed = d1.getBreed();
		System.out.println(myBreed);
		myName = d2.getName();
		System.out.println(myName);
		myBoldnessLevel = d2.getBoldnessLevel();
		System.out.println(myBoldnessLevel);
		myBreed = d2.getBreed();
		System.out.println(myBreed);

		// 6. We ensure that the two dogs are not the same
		if (d1 == d2){
			System.out.println("Same dog");
		}
		else{
			System.out.println("Different dog");
		}

		// 7. We ensure that the two toys of the dogs are not the same
		Toy f = d1.getToy();
		Toy s = d2.getToy();
		if (f == s){
			System.out.println("Same toy");
		}
		else{
			System.out.println("Different toy");
		}

		// 8. We get the toy field from d2
		Toy t2 = d2.getToy();

		// 9. We modify the toy resistance
		t2.setResistance(5);

		// 10. We check the resistance of t1 and t2
		int i = t1.getResistance();
		System.out.println(i);

		i = t2.getResistance();
		System.out.println(i);
	}


	//--------------------------------------------------
	//	MAIN
	//--------------------------------------------------
	/**
	 * Main Method:<br>
	 * 1) It test the functionality of the first constructor of Dog.<br>
	 * 2) It test the functionality of the second constructor of Dog.<br>
	 * 3) It test the functionality of the copy constructor of Dog in shallow mode.<br>
	 * 4) It test the functionality of the copy constructor of Dog in deep mode.<br>
	 * @param args: We will run the program parameter free, so do not worry about it.
	 */
	public static void main(String[] args) {
		// 1. We parse any input arguments
		int option = 1;

		// 2. We call to the method we want to try
		switch (option) {
			case 1:
				testConstructor1();
				break;
			case 2:
				testConstructor2();
				break;
			case 3:
				testCopyConstructor(true);
				break;
			case 4:
				testCopyConstructor(false);
				break;
		}

	}

}
