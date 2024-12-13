//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package org.example;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public final class ShopImp implements Shop{
    // Fields

    // Null

    // Constructors

    public ShopImp(){

        // Initialising the customer and computer databases rather than keeping them in a list

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
        );
        Database.createTable(Database.url,
                "CREATE TABLE IF NOT EXISTS orders(" +
                        "orderNo INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "customerNo INTEGER," +
                        "productNo INTEGER," +
                        "cost REAL," +
                        "FOREIGN KEY (customerNo) REFERENCES customer(customerNo)," +
                        "FOREIGN KEY (productNo) REFERENCES computer(productNo)" +
                        ");"
        );
    }

    // Methods

    @Override
    public void listItems() {
        var sqlSelect = "SELECT productNo,compName, stock, price FROM computers";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                System.out.printf(
                        "%-10s%-20s%-15s%-12s%n", // Formatting column
                        rs.getInt("productNo"),
                        rs.getString("compName"),
                        rs.getInt("stock"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

	
    @Override
    public void listCurrentStock() {
        var sqlSelect = "SELECT productNo,compName,stock FROM computers";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            System.out.printf("%-20s%-40s%-20s%n", "Product No.", "Company Name", "Stock");
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

    public void addCustomer(String name, int age, double currentBal) {
        boolean correctAge = Customer.checkAge(age);

        if (!correctAge) {
            System.out.println("You must be 16 or over to be a customer at this store");
        } else {
            new Customer(name, age, currentBal);
        }
    }
	

public void removeCustomer(int id, String name) {
        Scanner confirmingScanner = new Scanner(System.in);
    String confirm = "YES";
    System.out.println("To proceed please enter: the phrase " + "\"" + confirm + "\" ");
    String deletePrompt = Main.scanString(confirmingScanner,"Would you like to delete your user?",
                         1,30);
    if ((deletePrompt.strip()).equals(confirm)){
        var sql = "DELETE FROM customers WHERE customerNo = ? AND username = ?";

        try (var conn = DriverManager.getConnection(Database.url);
             var prepStmt = conn.prepareStatement(sql)) {

            prepStmt.setInt(1, id);
            prepStmt.setString(2, name);

            // execute the delete statement
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL Error please see below"); // Will throw if ID or name is unknown/bad combo
            System.err.println(e.getMessage());
        }
    }else{
        System.out.println("We hope not to see you again haha");
    }
}

@Override
public void displayCustomerInfo(int id) {
    var sqlSelect = "SELECT * FROM customers WHERE customerNo="+id+";";


    try (var conn = DriverManager.getConnection(Database.url);
         var stmt = conn.createStatement();
         var rs = stmt.executeQuery(sqlSelect)) {

        System.out.printf("%-5s%-20s%-4s%-20s%n", "No", "Name", "Age", "Balance");
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

    public void addMoneyToCustAccount(int id, double amount) {
        var sql = "UPDATE customers SET currentBal = ? WHERE customerNo = ?";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
            double balance = Customer.getCustomerBalance(id);
            double newAmount = balance + amount;

            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, id); // Set the customer ID as the second parameter

            // Update the database
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void purchaseItem(int productID, int customerID){

                    ArrayList<Object> C = Computer.retrievePurchaseData(productID);
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

							System.out.println("You don't have enough money sorry! ");	

						}else{

					// Updating user balance
                        Order newOrder = new Order(customerID, sqlProductNo, sqlProductCost);
						System.out.println("Thank you very much, the " + sqlProductName + " is a great choice");
                        Customer.takeMoneyFromAcc(sqlProductCost,customerID);
                        Product.stockDecremented(sqlProductNo);
                        Scanner receiptScan = new Scanner(System.in);

						String receiptDecision = Main.scanString(receiptScan,"Would you like a receipt too?(y/n)"
                                ,1, 1);
                        receiptScan.close();

                            if (receiptDecision.equals("y")) {
                                System.out.println("thanks for shopping with us, check for your receipt");
                                saveReceipt(newOrder);
                            } else {
                                System.out.println("thanks for shopping with us");
                            }
				}
					}
    }

    @Override
    public void listRecentOrders() {
        var sqlSelect = "SELECT * FROM orders";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                System.out.printf(
                        "%-10s%-10s%-10s%-12s%n", // Formatting column
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

		public void saveReceipt(Order o){
			String receipt = "";

        String fileName = o.getCustomerID()+".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
					receipt  = o.toString();
            writer.write(receipt);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        }


}
