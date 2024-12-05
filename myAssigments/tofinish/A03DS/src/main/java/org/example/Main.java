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
        System.out.print("4. Display All Customer IDs\n");
        System.out.print("5. Purchase an item\n");
        System.out.print("6. Return an Item\n");
        System.out.print("7. Display Item Info\n");
        System.out.print("8. Display All Item IDs\n");
        System.out.print("9. Return Item\n");
        System.out.println("\n\n");
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
        boolean running = true;
        while (running) {
            displayMenu();
            int starterChoice = menuChooser(0, 11);
            switch (starterChoice) {
                case 0:
                    running = false;
                    break;

                case 1:

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

                case 9:

                    break;

                case 10:

                    break;

                case 11:

                    break;

                default:
                    break;
            }
        }

    }
}
