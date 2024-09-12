// Author: Daniel Soden
// Purpose: First Java program

/**
 * The main class of our project.<br>.
 */

import java.util.Scanner;
import java.io.PrintWriter;
public class Main {

    //-------------------------------------
    //	myFunction
    //-------------------------------------
    public static void myFunction(){
        // 1. Print a message by the standard output
        System.out.println("Hello World!");

        //2. We can print the value of a variable
        int x = 3;
        for (int i = 0; i < 5; i++) {
            System.out.println(x);
        }

        //3. We can print to a file

        try {
            //1. We create the file for writing
            PrintWriter f = new PrintWriter("my_file.txt", "UTF-8");
            f.println("Hello, now I'm printing to a file from Java");
            f.close();
        }
        catch (Exception e) {
            System.out.println("Sorry but the file cannot be created for writing to it");
        }

        //4. We can ask users for some input and print it later.
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter something: ");
        String myText = sc.nextLine();
        System.out.println("You entered " + myText);
				sc.close();
    }

    //-------------------------------------
    //	main
    //-------------------------------------
    /**
     * Main Method:<br>
     * Main method of the class.<br>
     * @param args: We will run the program parameter free, so do not worry about it.
     */
    public static void main(String[] args) {
        myFunction();
    }

}

