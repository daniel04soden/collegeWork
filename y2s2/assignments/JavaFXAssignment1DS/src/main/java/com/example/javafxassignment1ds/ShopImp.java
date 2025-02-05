//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package com.example.javafxassignment1ds;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//--------------------------------------------------
//
//	FINAL CLASS Shop
//
//--------------------------------------------------


/**
 * This is the primary system implementation for the java computer shop application <br>.
 */
public final class ShopImp implements Shop{ // Implementation of shop interface in order to create shop methods
    //---------------------------------------
    //	Fields
    //---------------------------------------


    //---------------------------------------
    //	Constructor
    //---------------------------------------
    /**
     * This constructor does not take in any parameters
     * or return any values, instead it initialises the tables
     * for the shop on first run
     */

    public ShopImp(){

        // Initialising the customer and computer databases rather than keeping them in a list

        // Database design created through learning in said module

        Database.createTable(Database.url,
                "CREATE TABLE IF NOT EXISTS customers(" +
                        "customerNo INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                        "username text," +
                        "age INTEGER," +
                        "currentBal REAL" +
                        ");"
        ); // Handles creating a Customer table

        Database.createTable(Database.url,
                "CREATE TABLE IF NOT EXISTS computers(" +
                        "productNo INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                        "price REAL," +
                        "compName TEXT," +
                        "stock INTEGER," +
                        "amountOnOrder INTEGER," +
                        "ram INTEGER," +
                        "storage INTEGER," +
                        "CPU TEXT," +
                        "GPU TEXT" +
                        ");"
        ); // Handles creating computer product table
        Database.createTable(Database.url,
                "CREATE TABLE IF NOT EXISTS orders(" +
                        "orderNo INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "customerNo INTEGER," +
                        "productNo INTEGER," +
                        "cost REAL," +
                        "FOREIGN KEY (customerNo) REFERENCES customer(customerNo)," +
                        "FOREIGN KEY (productNo) REFERENCES computer(productNo)" +
                        ");"
        ); // Handles creating orders table
    }


    //---------------------------------------
    //	GET METHODS
    //---------------------------------------
        // Null
    //---------------------------------------
    //	SET METHODS
    //---------------------------------------
        // Null


    //---------------------------------------
    //	EXTRA METHODS
    //---------------------------------------


    //---------------------------------------
    //	listItems
    //---------------------------------------

    /**
     * This implementation reads the computers table
     * and lists all information relating to the items we sell
     */
    @Override
    public void listItems() {
        var sqlSelect = "SELECT productNo,compName, stock, price FROM computers"; // Select Main info to do with items


        try (var conn = DriverManager.getConnection(Database.url); // Connection to db
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            System.out.printf("%-20s%-40s%-20s%n","Product No.", "Computer Name","Price");
            System.out.println("--------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf(
                        "%-20s%-40s%-20s%n", // Formatting column
                        rs.getInt("productNo"), // Converting values to correct datatypes
                        rs.getString("compName"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); // Sql error
        }
    }


    //---------------------------------------
    //	listCurrentStock
    //---------------------------------------

    /**
     * This method reads the names,stock and productNo
     * from the computers table to inform the user on the stock levels
     * so they know what computers they can even buy beforehand
     */
    @Override
    public void listCurrentStock() { // Same logic as listing computers, just including stock
        var sqlSelect = "SELECT productNo,compName,stock FROM computers";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            System.out.printf("%-20s%-40s%-20s%n", "Product No.", "Computer Name", "Stock");
            System.out.println("--------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-20s%-40s%-20s%n",
                        rs.getInt("productNo"),
                        rs.getString("compName"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * Given a concrete name age and balance, a customer object
     * and added via sqlite
     * @param name - String name given by the customer
     * @param age - int age of the customer
     * @param currentBal - double current balance for the customer
     */
    public void addCustomer(String name, int age, double currentBal) {
        boolean correctAge = Customer.checkAge(age);

        if (!correctAge) {
            System.out.println("You must be 16 or over to be a customer at this store");
        } else {
            new Customer(name, age, currentBal);
        }
    }


    //---------------------------------------
    //	removeCustomer
    //---------------------------------------

    /**
     * Given a concrete name and id, a customer can be fully removed
     * from the sqlite database.
     * This method takes in some details about
     * the customer to ensure its actually them
     * such as a username and an id and if so they will have the account removed
     * @param id - int unique id customer
     * @param name - String name of the customer
     */
public void removeCustomer(int id, String name) {
        Scanner confirmingScanner = new Scanner(System.in);
    String confirm = "YES"; // Confirmation message
    System.out.println("To proceed please enter: the phrase " + "\"" + confirm + "\" ");
    String deletePrompt = Main.scanString(confirmingScanner,"Would you like to delete your user?",
                         1,30);
    if ((deletePrompt.strip()).equals(confirm)){
        var sql = "DELETE FROM customers WHERE customerNo = ? AND username = ?"; // Conditions for deletion

        try (var conn = DriverManager.getConnection(Database.url);
             var prepStmt = conn.prepareStatement(sql)) {

            prepStmt.setInt(1, id); // Setting id and int as above parameters
            prepStmt.setString(2, name);

            // execute the delete statement
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL Error please see below"); // Will throw if ID or name is unknown/bad combo
            System.err.println(e.getMessage());
        }
    }else{
        System.out.println("We hope not to see you again haha"); // if fails hope customer doesn't delete
    }
}

    //---------------------------------------
    //	displayCustomerInfo
    //---------------------------------------

    /**
     * Given a concrete id, all relevant details relating to a customer
     * can be listed.
     * This is achieved by taking in and id from the customer via the cli
     * and from there grabbing this data from the sqlite db
     * @param id - int unique id customer to find display info
     */
@Override
public void displayCustomerInfo(int id) {
    var sqlSelect = "SELECT * FROM customers WHERE customerNo="+id+";";


    try (var conn = DriverManager.getConnection(Database.url);
         var stmt = conn.createStatement();
         var rs = stmt.executeQuery(sqlSelect)) {

        System.out.printf("%-5s%-20s%-4s%-20s%n", "No", "Name", "Age", "Balance"); // Formatting select statement
        System.out.println("-------------------------------------------------------\n");
        while (rs.next()) {
            System.out.printf("%-5s%-20s%-4s%-20s%n",
                    rs.getInt("customerNo"),
                    rs.getString("username"),
                    rs.getInt("age"),
                    rs.getDouble("currentBal")
            );
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
}

    //---------------------------------------
    //	addMoneyToCustAccount
    //---------------------------------------

    /**
     * Given an id and amount of money to add
     * a customers balance will be updated with this amount, like an atm
     * We grab their balance using the static getCustomerBalance method
     * and then add the balance and new amount in order to update their original balance
     * @param id - int unique id of customer
     * @param amount - double amount of money to be added to customer account
     */
    public void addMoneyToCustAccount(int id, double amount) {
        var sql = "UPDATE customers SET currentBal = ? WHERE customerNo = ?";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
            double balance = Customer.getCustomerBalance(id); // Static customer method
            double newAmount = balance + amount;

            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, id); // Set the customer ID as the second parameter

            // Update the database
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //---------------------------------------
    //	purchaseItem
    //---------------------------------------

    /**
     * Given a concrete id and productNo a customer
     * is able to purchase an item which both adjusts their
     * balance and our stock levels.
     * this is the heart of all our code and where most other
     * fields and methods come together to form the shops purchasing system.
     * A customer order will be created, the stock will be decremented and money
     * will be taken out of the customers account.
     * @param productID - product no of which customer will buy
     * @param customerID - int unique id of the customer
     */
    public void purchaseItem(int productID, int customerID){

                    ArrayList<Object> C = Computer.retrievePurchaseData(productID); // Arraylist of Product info
                    int sqlProductNo = (int) C.get(0);
                    double sqlProductCost = (double) C.get(1);
                    String  sqlProductName = (String) C.get(2);

                    // Providing amount given
                    Scanner givenMoney = new Scanner(System.in);
					double amountGiven = Main.scanDouble(givenMoney,"That will be " + sqlProductCost +
                            ", please enter how much you have on hand:");

					if (sqlProductNo != productID) {
						System.out.println("HEY WHATS UP not same P/N");	

					} else {

						if (amountGiven < sqlProductCost) {

							System.out.println("You don't have enough money sorry! ");	// Unable to pay

						}else{

					// Updating user balance
                        Order newOrder = new Order(customerID, sqlProductNo, sqlProductCost);
						System.out.println("Thank you very much, the " + sqlProductName + " is a great choice");
                        Customer.takeMoneyFromAcc(sqlProductCost,customerID);

                    // Removing stock from system
                        Product.stockDecremented(sqlProductNo);

                    // Deciding if user needs receipt
                        Scanner receiptScan = new Scanner(System.in);

						String receiptDecision = Main.scanString(receiptScan,"Would you like a receipt too?(y/n)"
                        ,1, 1);
                        receiptScan.close(); // closing receipt scanner

                            if (receiptDecision.equals("y")) {
                                System.out.println("thanks for shopping with us, check for your receipt");
                                saveReceipt(newOrder);
                            } else {
                                System.out.println("thanks for shopping with us");
                            }
				}
					}
    }

    //---------------------------------------
    //	listRecentOrders
    //---------------------------------------

    /**
     * This method lists all orders coming in by looping over all info in the orders table
     * If there is no orders the output will just be blank
     */
    @Override
    public void listRecentOrders() {
        var sqlSelect = "SELECT * FROM orders";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            // Formatting select statement
            System.out.printf("%-10s%-10s%-10s%-12s%n", "OrderNo", "custNo", "productNo", "Price");
            System.out.println("-------------------------------------------------------\n");
            while (rs.next()) {
                System.out.printf(
                        "%-10s%-10s%-10s%-12s%n",// Formatting column
                        rs.getInt("orderNo"),
                        rs.getInt("customerNo"),
                        rs.getInt("productNo"),
                        rs.getDouble("cost")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //---------------------------------------
    //	saveReceipt
    //---------------------------------------

    /**
     * This method saves a receipt with information taken from the order class
     * in order to save as a txt file with the customers order no as the name of
     * the txt fie. Here we se file writer as a means of creating AND writing into
     * this file
     * @param o - Order created by customer to model the receipt
     */
		public void saveReceipt(Order o){
			String receipt;

        String fileName = o.getCustomerID()+".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
					receipt  = o.toString();
            writer.write(receipt);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        }


}
