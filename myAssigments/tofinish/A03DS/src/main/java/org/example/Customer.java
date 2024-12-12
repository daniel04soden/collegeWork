package org.example;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//--------------------------------------------------
//
//	CLASS Customer
//
//--------------------------------------------------

/**
 * This class models a user of the Shop<br>.
 */
public class Customer{

    //---------------------------------------
    //	Fields
    //---------------------------------------

    private int customerNo;
    public String username;
    public int age;
    double currentBal;

    //---------------------------------------
    //	Constructor
    //---------------------------------------

    /**
     * This constructor creates a model of a user and inserts it into the db
     * @param _username - intended username
     * @param _age - age of the customer
     * @param _currentBal - Honest balance provided by the customer
     */
    public Customer(String _username, int _age,double _currentBal){

        // Constructing the customer also involves putting them in the database
        String sqlStmt = "INSERT INTO customers(username, age, currentBal) VALUES(?, ?, ?)"; // Insert given values
        //SQLite handles default values

            try (var conn = DriverManager.getConnection(Database.url);
                 var prepStmt = conn.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS)) {
                prepStmt.setString(1, _username);
                prepStmt.setInt(2, _age);
                prepStmt.setDouble(3, _currentBal);
                prepStmt.executeUpdate();

                // Retrieve the generated customerID
                try (var rs = prepStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int customerID = rs.getInt(1); // Get generated id for output to user
												this.customerNo = customerID;
                        System.out.println("Thanks " + _username + " You are now a registered Customer\n");
                        System.out.println("------------------------------------------------");
                        System.out.println("Your given id is " + customerID + " ,Remember it!");
                    } else {
                        // Handle error: Failed to retrieve generated ID
                        System.err.println("Failed to retrieve generated customerID");
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        // Standard initialization of values

        this.username = _username;
        this.currentBal = _currentBal;
        this.age = _age;
        }

    //---------------------------------------
    //	GET METHODS
    //---------------------------------------

    public String getUsername() {
        return username;
    }


    public int getAge() {
        return age;
    }


    public int getCustomerNo() {
		return customerNo;
	}

    public static double getCustomerBalance(int id) {
        var sql = "SELECT currentBal FROM customers WHERE customerNo = ?";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Set the customer ID as the first parameter

            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("currentBal"); // Best case scenario
                } else { // Customer cannot be found
                    System.err.println("Customer " + id + " not found.");
                    return 0.0;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); // SQLError
            return 0.0;
        }
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Extra functionality

    public static boolean checkAge(int _age){
        return _age >= 16;
    } // Must be 16 or older


		public static void takeMoneyFromAcc(double amount,int customerID){
        var sql = "UPDATE customers SET currentBal = ? WHERE customerNo=" + customerID +";";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {

            double currentCustomerBalance = getCustomerBalance(customerID);

            if (amount > currentCustomerBalance){
                System.out.println("You do not have enough money in your account bozo");
            }else{
                // set the parameters
                pstmt.setDouble(1,getCustomerBalance(customerID) - amount);
                // update
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

	}

