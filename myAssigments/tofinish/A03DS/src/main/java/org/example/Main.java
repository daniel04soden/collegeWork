//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package org.example;

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
public class Main {

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

    public static int scanInt(Scanner scanning, String prompt){
        int res = -1;

        // 2. We create an auxiliary variable for the loop
        boolean valid = false;

        // 3. We keep asking for a valid option until one is given
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
        return Math.floor((res*100)) / 100;
    }

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

    public static String nameConversion(String name){
            if (name == null || name.isEmpty()) {
                return name;
            }else{
                return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            }
    }
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
            case 6 -> s.listCurrentStock(); // TODO Change to list Customer ids - Security issue but fuck it

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
    public static void main(String[] args) {
        shopSession();
    }
}
