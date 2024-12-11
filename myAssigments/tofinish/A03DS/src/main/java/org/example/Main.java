package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void displayMenu() {
        System.out.print("------------------------------------\n");
        System.out.print("\n");
        System.out.print("------------------------------------\n");
        System.out.print("0. Exit\n");
        System.out.print("1. Add Customer\n");
        System.out.print("2. Remove Customer\n");
        System.out.print("3. Display Customer Info\n");
        System.out.print("4. Purchase a computer\n");
        System.out.print("5. Display Item Info\n");
        System.out.print("6. Display All Item IDs\n");
        System.out.print("7. Add money to a customer account\n");
        System.out.print("8. List recent orders\n");
        System.out.println("\n");
        System.out.print("------------------------------------\n");
        System.out.print("------------------------------------\n");
    }

    public static int scanInt(Scanner scanning, String prompt,int lowRange, int upRange){
        int res = -1;
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            try {
                res = scanning.nextInt();
                scanning.nextLine();
                if ((res >= lowRange) && (res <= upRange)) {
                    valid = true;
                } else {
                    System.out.println("Please enter a value in the range" + lowRange + "and" + upRange + "!");
                }
            } catch (Exception e) {
                System.out.println("Please enter a real integer");
                scanning.next();
            }
        }
        return res;
    }


    public static int scanInt(Scanner scanning, String prompt){
        int res = -1;
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            try {
                res = scanning.nextInt();
                scanning.nextLine();
                    valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a real integer");
                scanning.next();
            }
        }
        return res;
    }

    public static double scanDouble(Scanner scanning, String prompt){
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
        return res;
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
                System.out.println("Please enter a real integer");
                scanning.next();
            }
        }
        return res;
    }
public static void session(){
        Scanner newScanner = new Scanner(System.in);
    ShopImp s = new ShopImp();
    Database.connect(Database.url);
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
                String name = scanString(newScanner,"Enter your name:", 30, 1);
                int age = scanInt(newScanner,"Enter your age");
                double balance = scanDouble(newScanner,"Enter the balance in your account now:");
                s.addCustomer(name, age, balance);
            }
            case 2 -> {

                int removeCustomerID = scanInt(newScanner,"Enter your customerID:");
                String custName = scanString(newScanner,"Confirm your customer name:", 30, 1);
                s.removeCustomer(removeCustomerID, custName);
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
            case 5 ->{
                s.listItems();
            }
            case 6 -> {
                s.listItemIDs();
            } // TODO Change to list Customer ids - Security issue but fuck it

            case 7 -> {
                int addId = Main.scanInt(newScanner,"What is your id?");
                double amountToAdd = Main.scanDouble(newScanner,"How much would you like to add to your account?");
                s.addMoneyToCustAccount(addId, amountToAdd);
            }
            case 8 -> {
                s.listRecentOrders();
            }

            default ->{
                System.out.println("Enter a valid option");
            }

        }
    }
}
    public static void main(String[] args) throws Exception {
        session();
    }
}
