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
        System.out.print("10. Save order as receipt (to disk)\n");
        System.out.println("\n");
        System.out.print("------------------------------------\n");
        System.out.print("------------------------------------\n");
    }

    public static int scanInt(String prompt, int maxLen, int minLen){
        Scanner scanning = new Scanner(System.in);
        int ans = 0;
        int length = String.valueOf(ans).length();
        while(length >=minLen && length < maxLen){
            System.out.println(prompt);
            ans = scanning.nextInt();
            length = String.valueOf(ans).length();
        }
        return ans;
    }

    public static String scanString(String prompt, int maxLen, int minLen){
        String ans = " ";
        Scanner scanning = new Scanner(System.in);
        while( ans.length() >= minLen && ans.length() < maxLen){
            System.out.println(prompt);
            ans = scanning.next().strip();
        }
        return ans;
    }

    public static Double scanDouble(String prompt, int maxLen, int minLen){
        Scanner scanning = new Scanner(System.in);
        double ans = 0.0;
        int length = String.valueOf(ans).length();
        while(length >=minLen && length < maxLen){
            System.out.println(prompt);
            ans = scanning.nextDouble();
            length = String.valueOf(ans).length();
        }
        return ans;
    }
    public static int menuChooser(int startRange, int endRange) {
        Scanner scanning = new Scanner(System.in);
        int nextAnswer = scanning.nextInt();
        boolean inRange = nextAnswer >= startRange || nextAnswer <= endRange;
        if (!inRange) {
            System.err.println("error: Please select an item between" + startRange + "and" + endRange);
        }
        scanning.close();
        return nextAnswer;
    }

    public static void main(String[] args) throws Exception {
        ShopImp s = new ShopImp();
        Database.connect(Database.url);
        boolean running = true;
        while (running) {
            displayMenu();
            int starterChoice = menuChooser(0, 11);
            switch (starterChoice) {
                case 0:
                    System.out.println("bye!");
                    running = false;
                    break;

                case 1:
                    Scanner scanning = new Scanner(System.in);
                    String name = scanString("Enter your name:",30,1);

                    System.out.println("Enter an id:  (Must be 9 characters long)");
                    int customerID = scanInt("Enter an id: (must be 9 characters long)",9,9);

                    System.out.println("Enter your age:  ");
                    int age = scanning.nextInt();

                    System.out.println("Enter the balance currently in your account:");
                    double balance = scanning.nextDouble();

                    s.addCustomer(customerID,name,age,balance);


                    break;

                case 2:
                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;


                default:
                    System.out.println("Pick a valid option");
            }
        }

    }
}
