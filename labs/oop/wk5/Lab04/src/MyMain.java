/**
 * Our main class for the inheritance class hierarchy example<br>.
 */

public class MyMain {

	//--------------------------------------------------
	//	testAnimalParentClass
	//--------------------------------------------------
	/**
	 * This function tests the parent class hierarchy.<br>
	 */
	public static void testAnimalParentClass(){
		System.out.print("\n------------------------------\n\n   TESTING CLASS ANIMAL \n\n------------------------------\n\n");

		// 1. We test the parent class
		Animal p = new Animal("Tom", 5);

		// 2. We test the get methods
		String s = p.getName();
		System.out.println(s);

		int i = p.getAge();
		System.out.println(i);

		// 3. We test the set methods
		p.setAge(7);
		p.setName("Thomas");

		// 4. We test the get methods again
		s = p.getName();
		System.out.println(s);

		i = p.getAge();
		System.out.println(i);
	}


	//--------------------------------------------------
	//	testDogChildClass
	//--------------------------------------------------
	/**
	 * This function tests the first child class hierarchy.<br>
	 */
	public static void testDogChildClass(){
		System.out.print("\n------------------------------\n\n   TESTING CLASS DOG \n\n------------------------------\n\n");

		// 1. We create a first dog using the first constructor
		Dog c1 = new Dog("Tom", 5, 6);

		// 2. We test the parent fields and methods
		String s = c1.getName();
		System.out.println(s);

		int i = c1.getAge();
		System.out.println(i);

		// 3. We test the parent set methods
		c1.setAge(7);
		c1.setName("Thomas");

		// 4. We test the parent get methods again
		s = c1.getName();
		System.out.println(s);

		i = c1.getAge();
		System.out.println(i);

		// 5. We test the child extra field boldnessLevel
		i = c1.getBoldnessLevel();
		System.out.println(i);

		c1.setBoldnessLevel(27);
		i = c1.getBoldnessLevel();
		System.out.println(i);

		// 6. We test the child extra field myToy
		DogToy d1 = c1.getMyToy();
		System.out.println(d1);

		DogToy d2 = new DogToy("Dogdog", true);
		c1.setMyToy(d2);

		DogToy d3 = c1.getMyToy();
		System.out.println(d3);

		// 7. We test the second constructor, the one using an existing toy
		Dog c2 = new Dog("Ron", 7, 8, d2);

		DogToy d4 = c2.getMyToy();
		System.out.println(d4);

		// 8. We test the extra methods
		for (int j = 0; j < 10; j++){
			c1.playWithToy();
			i = c1.getBoldnessLevel();
			System.out.println(i);
		}

		c1.bark(2);
		c1.bark(4);
	}


	//--------------------------------------------------
	//	testCatChildClass
	//--------------------------------------------------
	/**
	 * This function tests the first child class hierarchy.<br>
	 */
	public static void testCatChildClass(){
		System.out.print("\n------------------------------\n\n   TESTING CLASS CAT \n\n------------------------------\n\n");

		// 1. We create a first cat using the first constructor
		Cat c1 = new Cat("Toby", 5, 6);

		// 2. We test the parent fields and methods
		String s = c1.getName();
		System.out.println(s);

		int i = c1.getAge();
		System.out.println(i);

		// 3. We test the parent set methods
		c1.setAge(7);
		c1.setName("Tobias");

		// 4. We test the parent get methods again
		s = c1.getName();
		System.out.println(s);

		i = c1.getAge();
		System.out.println(i);

		// 5. We test the child extra field attentionLevel
		i = c1.getAttentionLevel();
		System.out.println(i);

		c1.setAttentionLevel(3);
		i = c1.getAttentionLevel();
		System.out.println(i);

		// 6. We test the child extra method
		for (int j = 0; j < 10; j++){
			boolean b = c1.isPayingAttention();
			System.out.println(b);
		}

	}


	//--------------------------------------------------
	//	testDogChildClass
	//--------------------------------------------------
	/**
	 * This function tests the first child class hierarchy.<br>
	 */
	public static void testGoldenRetrieverGrandchildClass(){
		System.out.print("\n------------------------------\n\n   TESTING CLASS GOLDEN RETRIEVER \n\n------------------------------\n\n");

		// 1. We create a first dog using the first constructor
		GoldenRetriever c1 = new GoldenRetriever("Tom", 5, 6, 3);

		// 2. We test the grand parent fields and methods
		String s = c1.getName();
		System.out.println(s);

		int i = c1.getAge();
		System.out.println(i);

		// 3. We test the grand parent set methods
		c1.setAge(7);
		c1.setName("Thomas");

		// 4. We test the grand parent get methods again
		s = c1.getName();
		System.out.println(s);

		i = c1.getAge();
		System.out.println(i);

		// 5. We test the parent extra field boldnessLevel
		i = c1.getBoldnessLevel();
		System.out.println(i);

		c1.setBoldnessLevel(27);
		i = c1.getBoldnessLevel();
		System.out.println(i);

		// 6. We test the parent extra field myToy
		DogToy d1 = c1.getMyToy();
		System.out.println(d1);

		DogToy d2 = new DogToy("Dogdog", true);
		c1.setMyToy(d2);

		DogToy d3 = c1.getMyToy();
		System.out.println(d3);

		// 7. We test the parent extra methods
		for (int j = 0; j < 10; j++){
			c1.playWithToy();
			i = c1.getBoldnessLevel();
			System.out.println(i);
		}

		c1.bark(2);
		c1.bark(4);

		// 8. We test the child extra field and methods
		i = c1.getLazinessLevel();
		System.out.println(i);

		c1.setLazinessLevel(8);

		i = c1.getLazinessLevel();
		System.out.println(i);

		for (int j = 0; j < 10; j++){
			i = c1.hoursLazy();
			System.out.println(i);
		}

	}


	//--------------------------------------------------
	//	main
	//--------------------------------------------------
	/**
	 * This is the main entry point for the Java application.<br>
	 */
	public static void main(String[] args) {
		// 1. We parse any input arguments
		int option = 4;

		// 2. We call to the method we want to try
		switch (option) {
			case 1:
				testAnimalParentClass();
				break;
			case 2:
				testDogChildClass();
				break;
			case 3:
				testCatChildClass();
				break;
			case 4:
				testGoldenRetrieverGrandchildClass();
				break;
		}
	}

}
