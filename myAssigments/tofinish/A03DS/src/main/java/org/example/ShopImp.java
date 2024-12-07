package org.example;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public final class ShopImp implements Shop{
    // Fields

    // Null

    // Constructors

    public ShopImp(){

        // Initialising the customer and computer databases rather than keeping them in a list

        Database.createTable(Database.url,
                "CREATE TABLE customers IF NOT EXISTS(" +
                        "customerID INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                        "username text," +
                        "age INTEGER," +
                        "currentBal REAL" +
                        ");"
        ); // Handles creating a Customer table

        Database.createTable(Database.url,
                "CREATE TABLE computers IF NOT EXISTS(" +
                        "productID INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
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
                "CREATE TABLE orders IF NOT EXISTS(" +
                        "orderID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "customerID INTEGER," +
                        "productID INTEGER," +
                        "cost REAL," +
                        "FOREIGN KEY (customerID) REFERENCES customer(customerID)," +
                        "FOREIGN KEY (productID) REFERENCES computer(productID)" +
                        ");"
        );
    }

    // Methods

    @Override
    public void listItems() {
        var sqlSelect = "SELECT productID,compName, stock, price FROM computers";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                System.out.printf(
                        "%-10s%-20s%-15s%-12s%n", // Formatting column
                        rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getInt("stock"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void orderStock(int productID) {

    }

    public Customer addCustomer(String name, int age, double currentBal) {
        boolean correctAge = Customer.checkAge(age);

        if (!correctAge) {
            System.out.println("You must be over 18 to be a customer at this store");
            return null;
        } else {
            String sqlStmt = "INSERT INTO customers(customerID, username, age, currentBal) VALUES(DEFAULT, ?, ?, ?)";

            try (var conn = DriverManager.getConnection(Database.url);
                 var prepStmt = conn.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS)) {
                prepStmt.setString(1, name);
                prepStmt.setInt(2, age);
                prepStmt.setDouble(3, currentBal);
                prepStmt.executeUpdate();

                // Retrieve the generated customerID
                try (var rs = prepStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int customerID = rs.getInt(1);
                        System.out.println("Thanks " + name + "You are now a registered Customer\n");
                        System.out.println("------------------------------------------------");
                        System.out.println("Your given id is " + customerID +" ,Remember it!");
                        return new Customer(customerID, name, age, currentBal);
                    } else {
                        // Handle error: Failed to retrieve generated ID
                        System.err.println("Failed to retrieve generated customerID");
                        return null;
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
    }

public void removeCustomer(Customer c, String name) {
    String confirm = "YES I WANT TO PROCEED TO DELETE MY THIS CUSTOMER";
    String deletePrompt = Main.scanString("Would you like to delete your user?",255,1);
    System.out.println("To proceed please enter:   " + confirm);
    if (deletePrompt.equals(confirm)){
        var sql = "DELETE FROM customer WHERE id = ?";
        var id = c.getId();

        try (var conn = DriverManager.getConnection(Database.url);
             var prepStmt = conn.prepareStatement(sql)) {

            prepStmt.setInt(1, id);

            // execute the delete statement
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        c = null;
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
                    rs.getInt("customerID"),
                    rs.getString("username"),
                    rs.getInt("age"),
                    rs.getDouble("currentBal")
            );
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
}

public void addMoneyToCustAccount(Customer c) {
    int id = Main.scanInt("Enter your user id to add money to your account:",9,9);
    double amount = Main.scanDouble("How much do you want to add?",100,1);
    c.addMoneyToAccount(amount);

    var sql = "UPDATE customers SET currentBal = ? WHERE customerID = "+c.getId();

    try (var conn = DriverManager.getConnection(Database.url);
         var prepstmt = conn.prepareStatement(sql)) {
        // set the parameters
        prepstmt.setDouble(1, amount);
        // update
        prepstmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println(e.getMessage());


        System.out.println("You have successfully added €" + amount + " to your account and now have €" + c.currentBal);
		}
    }

    public double purchaseItem(int productNo, Customer c, double amountGiven){
			double changeGiven = 0.0;
			
			var sqlProductLookup = "SELECT productNo,productName,price,stock FROM computers WHERE productNo=" + productNo;

			try (var conn = DriverManager.getConnection(Database.url);
					var prepStmt = conn.createStatement();
					var rs = prepStmt.executeQuery(sqlProductLookup)){

					int sqlProductNo = rs.getInt("productNo");
					String sqlProductName = rs.getString("productName");
					Double sqlProductCost= rs.getDouble("price");

					if (sqlProductNo != productNo) {
						System.out.println("HEY WHATS UP not same P/N");	

					} else {

						if (amountGiven < sqlProductCost) {

							System.out.println("You don't have enough money sorry! ");	

						}else{
					// Updating user balance
						changeGiven = amountGiven - sqlProductCost;
						c.takeMoneyFromAcc(sqlProductCost);	
						
						System.out.println("Thank you very much the" + sqlProductName + "is a great choice");
					// Updating stock


				}	
					}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		return changeGiven;
			
    }



}
