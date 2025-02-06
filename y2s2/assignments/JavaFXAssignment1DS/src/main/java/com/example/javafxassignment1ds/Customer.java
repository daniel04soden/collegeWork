//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package com.example.javafxassignment1ds;

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

    private int customerNo; // Customer unique identifier
    public String username; // Customer given username
    public int age; // Customer age (16 or over)
    double currentBal; // Current bal in euros in customer account

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

    //---------------------------------------
    //	getUsername
    //---------------------------------------

    /**
     * Given a concrete Customer, this method returns their name
     * @return Customer Username
     */
    public String getUsername() {
        return username;
    }


    //---------------------------------------
    //	getAge
    //---------------------------------------

    /**
     * Given a concrete customer, this method returns their age
     * @return Customer Age
     */
    public int getAge() {
        return age;
    }


    //---------------------------------------
    //	getCustomerNo
    //---------------------------------------

    /**
     * Given a concrete customer, this method returns the customer id
     * @return Customer id
     */
    public int getCustomerNo() {
        return customerNo;
    }

    //---------------------------------------
    //	getCustomerBalance
    //---------------------------------------
    /**
     * Given a concrete customerID, this method goes through the sqlite
     * database and returns the double for their current balance
     * @param id - int - Customer id to find balance
     * @return the Customers balance - if invalid search return 0.0
     */

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

    //---------------------------------------
    //	SET METHODS
    //---------------------------------------


    //---------------------------------------
    //	setUsername
    //---------------------------------------
    /**
     * Given a concrete customer, this method sets their new username with a given name
     * @param username - new username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    //---------------------------------------
    //	setAge
    //---------------------------------------
    /**
     * Given a concrete customer and new age, this method sets the customers age
     * @param age - updated age
     */
    public void setAge(int age) {
        this.age = age;
    }

    //---------------------------------------
    //	EXTRA METHODS
    //---------------------------------------

    //---------------------------------------
    //	checkAge
    //---------------------------------------
    /**
     * @param _age - age provided by the customer
     * @return - boolean to check if the customer is or is older than 16
     */
    public static boolean checkAge(int _age){
        return _age >= 16;
    } // Must be 16 or older



    //---------------------------------------
    //	takeMoneyFromAcc
    //---------------------------------------

    /**
     * this method takes in an amount and a customers id in order to
     * take out a certain amount from their account, mainly used in the purchasing method
     * @param amount - Amount to be taken
     * @param customerID - ID of the customers account
     */
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

