// Author: Daniel Soden
// Purpose: Java 15% exam

public class Student {

    //---------------------------------------
    //	Fields
    //---------------------------------------
		private String name;
		private int id;
		private boolean memberOfProgSociety;

    //---------------------------------------
    //	Constructor
    //---------------------------------------
		public Student(String studentName, int studentId, boolean isMemberOfProgSociety){
		this.name = studentName;
		if (studentId < 100000 || studentId > 300000) { // if the given id is greater than 300000 or less than 100000 
			this.id = 300000; // Default id if condition met
		} else {
			this.id = studentId; // Id assigned correctly
		}
		this.memberOfProgSociety = isMemberOfProgSociety;
	}

    //---------------------------------------
    //	Get Methods
    //---------------------------------------

		public String getName() {
			return this.name;
		}

		public int getId() {
			return this.id;
		}

		public boolean getMemberOfProgSociety() {
			return this.memberOfProgSociety;
		}

    //---------------------------------------
    //	Set Methods
    //---------------------------------------

		public void setName(String name) {
			this.name = name;
		}


		public void setId(int id) {
			this.id = id;
		}


		public void setMemberOfProgSociety(boolean memberOfProgSociety) {
			this.memberOfProgSociety = memberOfProgSociety;
		}


    //---------------------------------------
    //	Extra Functionality
    //---------------------------------------
			public void printStudentInfo(int option) {
		/*This function prints out various pieces of information about students
		 * At 1 it'll print the name
		 * At 2 it'll print their id
		 * And at 3 it will check if the student is part of that society and from there it will push you to join if not*/
		if (option == 1) {
			System.out.println(this.name);
			
		} else if (option ==2){
			System.out.println(this.id);
			
		}else if (option ==3){
				if (memberOfProgSociety == true) {
					System.out.println("Thank you for joining the Programming Society");	
				} else {
					System.out.println("Please consider joining the Programming Society");	
				}
		}else{
			System.out.println();
		}
			}

	public int sumOfIDDigits() {
		/*this function accesses the class student and separates it into 
		 * a String of characters and from here it turns those characters into numbers and adds them together
		 * String and character methods learned from java documentation myself previously*/
		String seperatedId = Integer.toString(this.id); // Turn id into a long string of nums(12345 = 1 2 3 4 5)
		int sum = 0; // Initialise sum

		for (int i = 0; i < seperatedId.length(); i++) {
			char individualId = seperatedId.charAt(i); // Each number is now its own character
			sum+= Character.getNumericValue(individualId); // Add each individual numeric character until length of string hit
		}
		return sum;
	}

}
