//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package com.example.javafxassignment1ds;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.InputMismatchException;
import java.util.Scanner;

//--------------------------------------------------
//
//	CLASS Main
//
//--------------------------------------------------

/**
 * This is the main class for the A03 Assignment
 * representing a computer Shop<br>
 */
public class Main {

    // --------------------------------------------------
    // displayMenu
    // --------------------------------------------------
    /**
     * This function displays the menu by the screen to the user.<br>
     */
    public static void displayMenu() {
        System.out.print("------------------------------------\n");
        System.out.print("\n");
        System.out.print("------------------------------------\n");
        System.out.print("0. Exit\n");
        System.out.print("1. Add Customer\n");
        System.out.print("2. Remove Customer\n");
        System.out.print("3. Review personal Details\n");
        System.out.print("4. Purchase a computer\n");
        System.out.print("5. List Our computers\n");
        System.out.print("6. Check our stock\n");
        System.out.print("7. Withdraw cash\n");
        System.out.print("8. List our recent orders\n");
        System.out.println("\n");
        System.out.print("------------------------------------\n");
        System.out.print("------------------------------------\n");
    }
    // --------------------------------------------------
    // scanInt
    // --------------------------------------------------
    /**
     * This function asks the user to enter a valid integer value (within a range)
     *.<br>
     *
     * @param scanning - The scanner taking in user input
     * @param lowRange - Lowest Menu value
     * @param upRange - Highest Menu value
     * @param prompt -  Prompt to give the user as input
     * @return - Integer entered by user.
     */
    public static int scanInt(Scanner scanning, String prompt, int lowRange, int upRange) {
        int res = -1;

        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            try {
                res = scanning.nextInt();
                scanning.nextLine();
                if ((res >= lowRange) && (res <= upRange))
                    valid = true;
                else
                    System.out.println("Sorry but the option must be within the range " + lowRange + " and " + upRange);
            } catch (Exception e) {
                System.out.println("Sorry you did not enter an integer");
                scanning.next();
            }
        }

        return res;
    }

    // --------------------------------------------------
    // scanInt
    // --------------------------------------------------
    /**
     * This function asks the user to enter a valid integer value
     *.<br>
     *
     * @param scanning - The scanner taking in user input
     * @param prompt -  Prompt to give the user as input
     * @return - Integer entered by user.
     */
    public static int scanInt(Scanner scanning, String prompt){
        int res = -1;

        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            try {
                res = scanning.nextInt();
                scanning.nextLine();
                valid = true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println("Sorry you did not enter an integer and then press the return key");
                scanning.next();
            }
        }
            return res;
    }

    // --------------------------------------------------
    // scanDouble
    // --------------------------------------------------
    /**
     * This function asks the user to enter a valid double value
     *.<br>
     *
     * @param scanning - The scanner taking in user input
     * @param prompt -  Prompt to give the user as input
     * @return - double entered by user.
     */
    public static double scanDouble(Scanner scanning, String prompt){ // Taking in a double make sure it's currency
        double res = 0.0;
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            try {
                res = scanning.nextDouble();
                scanning.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a real double");
                scanning.next();
            }
        }
        return Math.floor((res*100)) / 100; // Ensuring value is to two decimal places
    }

    // --------------------------------------------------
    // scanString
    // --------------------------------------------------
    /**
     * This function asks the user to enter a valid string value
     *.<br>
     *
     * @param scanning - The scanner taking in user input
     * @param prompt -  Prompt to give the user as input
     * @param minLen -  minimum length of the string
     * @param maxLen -  maximum length of the string
     * @return - string entered by user.
     */
    public static String scanString(Scanner scanning, String prompt,int minLen, int maxLen){
        String res = "";
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            try {
                res = scanning.next();
                scanning.nextLine();
                boolean validString = (res.length() >= minLen) && (res.length() <= maxLen);

                if (validString) {
                    valid = true;
                } else {
                    System.out.println("Make sure your answer isn't greater than" + maxLen + "or less than" + minLen);
                }
            } catch (Exception e) {
                System.out.println("Please enter a real string");
                scanning.next();
            }
        }
        return res;
    }

    // --------------------------------------------------
    // nameConversion
    // --------------------------------------------------
    /**
     * This function asks the user to enter a valid string value
     *.<br>
     *
     * @param name - turns all names into strings with a capital at the start
     * @return - string given by user in capital format
     */
    public static String nameConversion(String name){
            if (name.isEmpty()) {
                return name;
            }else{
                return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            }
    }
    // --------------------------------------------------
    // shopSession
    // --------------------------------------------------
    /**
     * This function starts the interactive shop session for the user
     *.<br>
     */
public static void shopSession(){
        Scanner newScanner = new Scanner(System.in);
    ShopImp s = new ShopImp();
    boolean running = true;
    while (running) {
        displayMenu();
        int starterChoice = scanInt(newScanner,"Enter menu choice",0,8);

        switch (starterChoice){
            case 0 ->{
                System.out.println("bye!");
                running = false;
            }

            case 1 -> {
                String name = scanString(newScanner,"Enter your name:", 1, 30);
                int age = scanInt(newScanner,"Enter your age");
                double balance = scanDouble(newScanner,"Enter the balance in your account now:");
                s.addCustomer(nameConversion(name),age,balance);
            }
            case 2 -> {

                int removeCustomerID = scanInt(newScanner,"Enter your customerID:");
                String custName = scanString(newScanner,"Confirm your customer name:", 1, 30);
                s.removeCustomer(removeCustomerID, nameConversion(custName));
            }
            case 3 -> {
                int displayId = scanInt(newScanner, "What is your customer id");
                s.displayCustomerInfo(displayId);
            }
            case 4 -> {
                s.listItems();
                int chosenProduct = scanInt(newScanner,"What product do you want to purchase? ");
                int buyId = scanInt(newScanner,"What is your customer id");
                s.purchaseItem(chosenProduct, buyId);
            }
            case 5 -> s.listItems();

            case 6 -> s.listCurrentStock();

            case 7 -> {
                int addId = Main.scanInt(newScanner,"What is your id?");
                double amountToAdd = Main.scanDouble(newScanner,
                        "How much would you like to add to your account?");
                s.addMoneyToCustAccount(addId, amountToAdd);
            }
            case 8 -> s.listRecentOrders();
            default -> {
                System.out.println("Enter a valid option");
                displayMenu();
            }

        }
    }
    newScanner.close();
    }
    // --------------------------------------------------
    // main
    // --------------------------------------------------
    /**
     * This is the main method for the Java shop application.<br>
     *
     * @param args - Any argument passed when calling to the Java application.
     */
}
