package org.example;

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
        System.out.print("5. Return an Item\n");
        System.out.print("6. Display Item Info\n");
        System.out.print("7. Display All Item IDs\n");
        System.out.print("8. Add money to a customer account\n");
        System.out.print("9. List recent orders\n");
        System.out.println("\n");
        System.out.print("------------------------------------\n");
        System.out.print("------------------------------------\n");
    }

    public static int scanInt(String prompt){
        Scanner scanning = new Scanner(System.in);
        System.out.println(prompt);
        int res = scanning.nextInt();
        scanning.close();

        return res;
    }
    public static String scanString(String prompt, int maxLen, int minLen){
        Scanner scanning = new Scanner(System.in);
        String ans = " ";
        while( !(ans.length() >= minLen && ans.length() < maxLen)){
            System.out.println(prompt);
            ans = scanning.next();
            scanning.close();
        }
        return ans;
    }

    public static Double scanDouble(String prompt){
        Scanner scanning = new Scanner(System.in);
        System.out.println(prompt);
        double res = scanning.nextDouble();
        scanning.close();

        return res;
    }

    public static void main(String[] args) throws Exception {
        ShopImp s = new ShopImp();
        Database.connect(Database.url);
        boolean running = true;
        while (running) {
            displayMenu();
            int starterChoice = scanInt("Enter menu choice");
            switch (starterChoice) {
                case 0:
                    System.out.println("bye!");
                    running = false;
                    break; // Added break to exit the loop

                case 1:
                    String name = scanString("Enter your name:", 30, 1);
                    int age = scanInt("Enter your age");
                    double balance = scanDouble("Enter the balance in your account now:");
                    s.addCustomer(name, age, balance);
                    break;

                case 2:
                    int removeCustomerID = scanInt("Enter your customerID:");
                    String custName = scanString("Confirm your customer name:", 30, 1);
                    s.removeCustomer(removeCustomerID, custName);
                    break;

                case 3:
                    int displayId = scanInt("What is your customer id");
                    s.displayCustomerInfo(displayId);
                    break;

                case 4:
                    s.listItems();
                    int chosenProduct = scanInt("What product do you want to purchase? ");
                    int buyId = scanInt("What is your customer id");
                    s.purchaseItem(chosenProduct, buyId);
                    break;

                case 5:
                    // ... (implement case 5 logic)
                    break;

                case 6:
                    s.listItems();
                    break;

                case 7:
                    s.listItemIDs();
                    break;

                case 8:
                    int addId = Main.scanInt("What is your id?");
                    double amountToAdd = Main.scanDouble("How much would you like to add to your account?");
                    s.addMoneyToCustAccount(addId, amountToAdd);
                    break;

                case 9:
                    s.listRecentOrders();
                    break;

                default:
                    System.out.println("Pick a valid option");
            }
        }
    }
}