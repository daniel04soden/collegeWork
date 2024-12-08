package org.example;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;


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
    public void listItemIDs() {
        var sqlSelect = "SELECT productNo FROM computers";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                System.out.printf(
                        "%-10s%n", // Formatting column
                        rs.getInt("productNo")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Customer addCustomer(String name, int age, double currentBal) {
				Customer newCustomer = null;
        boolean correctAge = Customer.checkAge(age);

        if (!correctAge) {
            System.out.println("You must be over 18 to be a customer at this store");
        } else {
			 newCustomer = new Customer(name, age, currentBal);
    }
			return newCustomer;
	}
	

public void removeCustomer(int id, String name) {
    String confirm = "YES I WANT TO PROCEED TO DELETE MY THIS CUSTOMER";
    String deletePrompt = Main.scanString("Would you like to delete your user?",255,1);
    System.out.println("To proceed please enter:   " + confirm);
    if (deletePrompt.equals(confirm)){
        var sql = "DELETE FROM customer WHERE sqlId = ?";
        var sqlId = id;

        try (var conn = DriverManager.getConnection(Database.url);
             var prepStmt = conn.prepareStatement(sql)) {

            prepStmt.setInt(1, sqlId);

            // execute the delete statement
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

@Override
public void displayCustomerInfo(int id) {
    var sqlSelect = "SELECT * FROM customers";


    try (var conn = DriverManager.getConnection(Database.url);
         var stmt = conn.createStatement();
         var rs = stmt.executeQuery(sqlSelect)) {

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


		public void addMoneyToCustAccount(int id,double amount){
        var sql = "UPDATE customers SET currentBal = ? WHERE customerNo=" + id +";";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
						double balance = Customer.getCustomerBalance(id);
            pstmt.setDouble(1, balance + amount);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void purchaseItem(int productID, int customerID){
			double changeGiven = 0.0;
			
			var sqlProductLookup = "SELECT productNo,compName,price,stock FROM computers WHERE productNo=" + productID + ";";

			try (var conn = DriverManager.getConnection(Database.url);
					var prepStmt = conn.createStatement();
					var rs = prepStmt.executeQuery(sqlProductLookup)){

					int sqlProductNo = rs.getInt("productNo");
					String sqlProductName = rs.getString("compName");
					Double sqlProductCost= rs.getDouble("price");

					double amountGiven = Main.scanDouble("That will be" + sqlProductCost + ", please enter how much you have on hand:");

					if (sqlProductNo != productID) {
						System.out.println("HEY WHATS UP not same P/N");	

					} else {

						if (amountGiven < sqlProductCost) {

							System.out.println("You don't have enough money sorry! ");	

						}else{

					// Updating user balance
						changeGiven = amountGiven - sqlProductCost;
						Customer.takeMoneyFromAcc(sqlProductCost,customerID);
                        Order newOrder = new Order(customerID, sqlProductNo, sqlProductCost);
						System.out.println("Thank you very much, the " + sqlProductName + " is a great choice");


						String receiptDecision = Main.scanString("Would you like a receipt too? ", 1, 1);

						switch (receiptDecision) {
							case "y":
								saveReceipt(newOrder);
                                System.out.println("thanks for shopping with us");
								break;

							default:
                                System.out.println("thanks for shopping with us");
								break;
						}

				}	
					}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		// Logging users order

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

		public String saveReceipt(Order o){
			String receipt = "";

        String fileName = o.getCustomerID()+".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
					receipt  = o.toString();
            writer.write(receipt);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

		return receipt;

	}

}
