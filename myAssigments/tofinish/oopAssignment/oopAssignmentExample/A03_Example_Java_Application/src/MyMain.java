
//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.Scanner;

//--------------------------------------------------
//
//	CLASS MyMain
//
//--------------------------------------------------
/**
 * This is the main class for the A03 Project Example,
 * representing the management of a library<br>.
 */
public class MyMain {


	//--------------------------------------------------
	//	printMenu
	//--------------------------------------------------
	/**
	 * This function prints the menu by the screen to the user.<br>
	 */
	public static void printMenu(){
		System.out.print("------------------------------------\n");
		System.out.print("			    MENU			  	  \n");
		System.out.print("------------------------------------\n");
		System.out.print("0. Exit\n");
		System.out.print("1. Add User\n");
		System.out.print("2. Remove User\n");
		System.out.print("3. Display User Info\n");
		System.out.print("4. Display All User IDs\n");
		System.out.print("5. Add Book Item\n");
		System.out.print("6. Add Music Album Item\n");
		System.out.print("7. Remove Item\n");
		System.out.print("8. Display Item Info\n");
		System.out.print("9. Display All Item IDs\n");
		System.out.print("10. Borrow Item\n");
		System.out.print("11. Return Item\n");
		System.out.print("12. Impose User Fine\n");
		System.out.print("13. Remove User Fine\n");
		System.out.print("14. Load Database from Disk\n");
		System.out.print("15. Save Database to Disk\n");
		System.out.println("\n\n");
	}

	//--------------------------------------------------
	//	selectIntOption
	//--------------------------------------------------
	/**
	 * This function asks the user to enter a valid integer value (within a range) by keyboard.<br>
	 * @param sc - The scanner getting the input value by keyboard
	 * @param lb - The lower bound value accepted.
	 * @param ub - The upper bound value accepted.
	 * @return - The value entered by keyboard.
	 */
	public static int selectIntOption(Scanner sc,
									  int lb,
									  int ub
	 								 ){

		//1. We create the output variable
		int res = -1;

		// 2. We create an auxiliary variable for the loop
		boolean validOption = false;

		//3. We keep asking for a valid option until one is given
		while (!validOption) {
			System.out.println("Please enter an integer value within the range " + lb + " and " + ub );
			try {
				res = sc.nextInt();
				sc.nextLine();
				if ((res >= lb) && (res <= ub))
					validOption = true;
				else
					System.out.println("Sorry but the option must be within the range " + lb + " and " + ub);
			}
			catch (Exception e) {
				System.out.println("Sorry you did not enter an integer and then press the return key");
				sc.next();
			}
		}

		//4. We return res
		return res;
	}


	//--------------------------------------------------
	//	selectIntOption
	//--------------------------------------------------
	/**
	 * This function asks the user to enter a valid integer value by keyboard.<br>
	 * @param sc - The scanner getting the input value by keyboard
	 * @return - The value entered by keyboard.
	 */
	public static int selectIntOption(Scanner sc){
		//1. We create the output variable
		int res = -1;

		// 2. We create an auxiliary variable for the loop
		boolean validOption = false;

		//3. We keep asking for a valid option until one is given
		while (!validOption) {
			System.out.println("Please enter an integer value");
			try {
				res = sc.nextInt();
				sc.nextLine();
				validOption = true;
			}
			catch (Exception e) {
				System.out.println("Sorry you did not enter an integer and then press the return key");
				sc.next();
			}
		}

		//4. We return res
		return res;
	}


	//--------------------------------------------------
	//	selectStringOption
	//--------------------------------------------------
	/**
	 * This function asks the user to enter a valid String value by keyboard.<br>
	 * @param sc - The scanner getting the input value by keyboard
	 * @return - The value entered by keyboard.
	 */
	public static String selectStringOption(Scanner sc){

		//1. We create the output variable
		String res = "";

		// 2. We create an auxiliary variable for the loop
		boolean validOption = false;

		//3. We keep asking for a valid option until one is given
		while (!validOption) {
			System.out.println("Please enter a String");
			try {
				res = sc.nextLine();
				if ((res.length() > 0) && (res.charAt(0) >= 'A') && (res.charAt(0) <= 'Z'))
					validOption = true;
				else
					System.out.println("Sorry but the option must be a non-empty string starting by a capital letter");
			}
			catch (Exception e) {
				System.out.println("Sorry you did not enter a String and then press the return key");
				sc.next();
			}
		}

		//4. We return res
		return res;
	}


	//--------------------------------------------------
	//	selectBooleanOption
	//--------------------------------------------------
	/**
	 * This function asks the user to enter a valid boolean value by keyboard.<br>
	 * @param sc - The scanner getting the input value by keyboard
	 * @return - The value entered by keyboard.
	 */
	public static boolean selectBooleanOption(Scanner sc){

		//1. We create the output variable
		boolean res = false;

		// 2. We create an auxiliary variable for the loop
		boolean validOption = false;

		//3. We keep asking for a valid option until one is given
		while (!validOption) {
			System.out.println("Please enter a boolean");
			try {
				res = sc.nextBoolean();
				validOption = true;
			}
			catch (Exception e) {
				System.out.println("Sorry you did not enter a boolean and then press the return key");
				sc.next();
			}
		}

		//4. We return res
		return res;
	}


	//-------------------------------------------------------------------
	// interactiveSession
	//-------------------------------------------------------------------
	/**
	 * This function performs the interactive session to the user.<br>
	 * @param usersFileName - The name of the database file for users.
	 * @param itemsFileName - The name of the database file for items.
	 * @param maxBorrowItems - The maximum amount of items a user can borrow.
	 */
	public static void interactiveSession(String usersFileName,
										  String itemsFileName,
										  int maxBorrowItems
										 ) {

		//1. We create the library database manager object
		Library l = new LibraryImp(maxBorrowItems);

		// 2. We create some auxiliary variables
		boolean finish = false;
		int option;
		Scanner sc = new Scanner(System.in);

		//3. While the user wants to continue the session
		while (!finish){
			//3.1. We clean the screen
			for (int i = 0; i < 100; i++)
				System.out.println("\n");

			// 3.2. We print the menu
			printMenu();

			// 3.3. We ask for an option
			option = selectIntOption(sc,
					              	 0,
					              	 15
			                        );

			//3.4. We clean the screen again
			for (int i = 0; i < 100; i++)
				System.out.println("\n");

			// 3.5. We create a few auxiliary variables
			String myStr;
			String myStr2;
			String myStr3;

			int myInt;
			int myInt2;
			int myInt3;

			boolean myBool;

			// 3.5. We perform the action
			switch (option) {
				case 0 -> finish = true;

				case 1 -> {
					// I. We print the message
					System.out.println("---------------\n1. Add User\n---------------");

					// II. We ask for the user input to define the user
					System.out.println("Please enter the name for the new user");
					myStr = selectStringOption(sc);

					// III. We attempt to add the user
					myInt = l.addUser(myStr);

					// IV. We inform of the success of the operation
					System.out.println("New user with id " + myInt + " successfully created");
				}

				case 2 -> {
					// I. We print the message
					System.out.println("---------------\n2. Remove User\n---------------");

					// II. We ask for the user input to identify the user to be removed
					System.out.println("Please enter the id for the user to be removed");
					myInt = selectIntOption(sc);

					// III. We attempt to remove the user
					myInt = l.removeUser(myInt);

					// IV. We inform of the success of the operation
					if (myInt == -1)
						System.out.println("Sorry, no user is registered with id = " + myInt + " so the remove operation cannot proceed.");
					else
						if (myInt == -2)
							System.out.println("Sorry, the user with id = " + myInt + " has pending borrows, so the remove operation cannot proceed.");
						else
							System.out.println("User with id = " + myInt + " successfully removed");
				}

				case 3 -> {
					// I. We print the message
					System.out.println("---------------\n3. Display User Info\n---------------");

					// II. We ask for the user input to identify the user to be removed
					System.out.println("Please enter the id for the user to be displayed");
					myInt = selectIntOption(sc);

					// III. We attempt to display the user info
					l.displayUserInfo(myInt);
				}

				case 4 -> {
					// I. We print the message
					System.out.println("---------------\n3. Display All User IDs\n---------------");

					// II. We perform the action
					l.displayAllUserIDs();
				}

				case 5 -> {
					// I. We print the message
					System.out.println("---------------\n5. Add Book Item\n---------------");

					// II. We ask for the user input to define the item
					System.out.println("Please enter the title for the new book");
					myStr = selectStringOption(sc);
					System.out.println("Please enter the author for the new book");
					myStr2 = selectStringOption(sc);
					System.out.println("Please enter the release year for the new book");
					myInt = selectIntOption(sc);
					System.out.println("Please enter the genre for the new book");
					myStr3 = selectStringOption(sc);
					System.out.println("Please enter the number of pages for the new book");
					myInt2 = selectIntOption(sc);

					// III. We attempt to add the book
					myInt = l.addBook(myStr,
							myStr2,
							myInt,
							myStr3,
							myInt2
					);

					// IV. We inform of the success of the operation
					System.out.println("New book with id " + myInt + " successfully created");
				}

				case 6 -> {
					// I. We print the message
					System.out.println("---------------\n6. Add Music Album Item\n---------------");

					// II. We ask for the user input to define the item
					System.out.println("Please enter the title for the new music album");
					myStr = selectStringOption(sc);
					System.out.println("Please enter the author for the new music album");
					myStr2 = selectStringOption(sc);
					System.out.println("Please enter the release year for the new music album");
					myInt = selectIntOption(sc);
					System.out.println("Please enter the genre for the new music album");
					myStr3 = selectStringOption(sc);
					System.out.println("Please enter if the new music album was album of the year");
					myBool = selectBooleanOption(sc);

					// III. We attempt to add the book
					myInt = l.addMusicAlbum(myStr,
							myStr2,
							myInt,
							myStr3,
							myBool
					);

					// IV. We inform of the success of the operation
					System.out.println("New music album with id " + myInt + " successfully created");
				}

				case 7 -> {
					// I. We print the message
					System.out.println("---------------\n7. Remove Item\n---------------");

					// II. We ask for the user input to identify the item to be removed
					System.out.println("Please enter the id for the item to be removed");
					myInt = selectIntOption(sc);

					// III. We attempt to remove the user
					myBool = l.removeItem(myInt);

					// IV. We inform of the success of the operation
					if (!myBool) {
						System.out.println("Sorry, no item is registered with id = " + myInt + " so the remove operation can not proceed.");
					} else
						System.out.println("Item with id = " + myInt + " successfully removed");
				}

				case 8 -> {
					// I. We print the message
					System.out.println("---------------\n8. Display Item Info\n---------------");

					// II. We ask for the user input to identify the item to be displayed
					System.out.println("Please enter the id for the item to be displayed");
					myInt = selectIntOption(sc);

					// III. We attempt to display the user info
					l.displayItemInfo(myInt);
				}

				case 9 -> {
					// I. We print the message
					System.out.println("---------------\n9. Display All Item IDs\n---------------");

					// II. We perform the action
					l.displayAllItemIDs();
				}

				case 10 -> {
					// I. We print the message
					System.out.println("---------------\n10. Borrow Item\n---------------");

					// II. We ask for the user input to identify the user id and the item id
					System.out.println("Please enter the id for the user borrowing the item");
					myInt = selectIntOption(sc);
					System.out.println("Please enter the id for the item to be borrowed");
					myInt2 = selectIntOption(sc);

					// III. We attempt to borrow the item
					myInt3 = l.borrowItem(myInt, myInt2);

					// IV. We inform of the success of the operation
					if (myInt3 == -1) {
						System.out.println("Sorry, the item can not be borrowed");
					} else
						System.out.println("New borrow with id = " + myInt3 + " successfully performed");
				}

				case 11 -> {
					// I. We print the message
					System.out.println("---------------\n11. Return Item\n---------------");

					// II. We ask for the user input to identify the user id and the item id
					System.out.println("Please enter the id for the user returning the item");
					myInt = selectIntOption(sc);
					System.out.println("Please enter the id for the item to be returned");
					myInt2 = selectIntOption(sc);

					// III. We attempt to borrow the item
					myBool = l.returnItem(myInt, myInt2);

					// IV. We inform of the success of the operation
					if (!myBool) {
						System.out.println("Sorry, the item can not be returned");
					} else
						System.out.println("The item is returned successfully.");
				}

				case 12 -> {
					// I. We print the message
					System.out.println("---------------\n12. Impose User Fine\n---------------");

					// II. We ask for the user input to identify the user to be removed
					System.out.println("Please enter the id for the user for which we want to remove the fine");
					myInt = selectIntOption(sc);

					// III. We attempt to display the user info
					myInt2 = l.imposeUserFine(myInt);

					// IV. We inform of the success of the operation
					if (myInt2 == -1)
						System.out.println("Sorry, no user is registered with id = " + myInt + " so the remove operation cannot proceed.");
					else
					if (myInt2 == -2)
						System.out.println("Sorry, the user with id = " + myInt + " was already fined.");
					else
						System.out.println("Fine for user with id = " + myInt + " successfully imposed");
				}

				case 13 -> {
					// I. We print the message
					System.out.println("---------------\n13. Remove User Fine\n---------------");

					// II. We ask for the user input to identify the user to be removed
					System.out.println("Please enter the id for the user for which we want to remove the fine");
					myInt = selectIntOption(sc);

					// III. We attempt to display the user info
					myInt2 = l.removeUserFine(myInt);

					// IV. We inform of the success of the operation
					if (myInt2 == -1)
						System.out.println("Sorry, no user is registered with id = " + myInt + " so the remove operation cannot proceed.");
					else
					if (myInt2 == -2)
						System.out.println("Sorry, the user with id = " + myInt + " was already not fined.");
					else
						System.out.println("Fine for user with id = " + myInt + " successfully removed");
				}

				case 14 -> {
					// I. We print the message
					System.out.println("---------------\n14. Load Database from Disk\n---------------");

					// II. We attempt to load the users, items and borrows of the library from disk.
					myBool = l.loadContentFromDisk(usersFileName,
												   itemsFileName
												  );

					// III. We inform of the success of the operation
					if (!myBool) {
						System.out.println("Sorry, the saving to disk can not be completed");
					} else
						System.out.println("The saving to disk is completed successfully.");
				}

				case 15 -> {
					// I. We print the message
					System.out.println("---------------\n15. Save Database to Disk\n---------------");

					// II. We attempt to save the users, items and borrows of the library to disk.
					myBool = l.saveContentToDisk(usersFileName,
							itemsFileName
					);

					// III. We inform of the success of the operation
					if (!myBool) {
						System.out.println("Sorry, the saving to disk can not be completed");
					} else
						System.out.println("The saving to disk is completed successfully.");
				}

			}

			if (option != 0) {
				System.out.println("Please key to continue");
				sc.nextLine();
			}
		}
	}


	//--------------------------------------------------
	//	main
	//--------------------------------------------------
	/**
	 * This is the main entry point for the Java application.<br>
	 * @param args - Any argument passed when calling to the Java application.
	 */
	public static void main(String[] args) {
		// 1. We parse any input arguments
		String usersFileName = "./myDatabase/users.txt";
		String itemsFileName = "./myDatabase/items.txt";
		int maxBorrowItems = 3;

		// 2. We call to the method we want to try
		interactiveSession(usersFileName,
				           itemsFileName,
				           maxBorrowItems
		                  );
	}

}
