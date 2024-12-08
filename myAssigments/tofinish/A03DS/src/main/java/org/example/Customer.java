package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer{

    // Fields
		
    private int customerNo;
    public String username;
    public int age;
    double currentBal;

    // Constructors

    public Customer(String _username, int _age,double _currentBal){
        boolean correctAge = Customer.checkAge(age);

        if (!correctAge) {
            System.out.println("You must be over 18 to be a customer at this store");
        } else {
            String sqlStmt = "INSERT INTO customers(customerID, username, age, currentBal) VALUES(DEFAULT, ?, ?, ?)";

            try (var conn = DriverManager.getConnection(Database.url);
                 var prepStmt = conn.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS)) {
                prepStmt.setString(1, _username);
                prepStmt.setInt(2, _age);
                prepStmt.setDouble(3, _currentBal);
                prepStmt.executeUpdate();

                // Retrieve the generated customerID
                try (var rs = prepStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int customerID = rs.getInt(1);
												this.customerNo = customerID;
                        System.out.println("Thanks " + _username + "You are now a registered Customer\n");
                        System.out.println("------------------------------------------------");
                        System.out.println("Your given id is " + customerID +" ,Remember it!");
                    } else {
                        // Handle error: Failed to retrieve generated ID
                        System.err.println("Failed to retrieve generated customerID");
                    }
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        this.username = _username;
        this.currentBal = _currentBal;
        this.age = _age;
    }

    // Getters and setters


    public double getCurrentBal() {
        return currentBal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

		public int getCustomerNo() {
		return customerNo;
	}
    // Extra functionality
    public static boolean checkAge(int _age){
        return _age >= 18;
    }


		public static void takeMoneyFromAcc(double amount,int customerID){
        var sql = "UPDATE customers SET currentBal = ? WHERE customerID=" + customerID +";";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
            // set the parameters
            pstmt.setDouble(1,getCustomerBalance(customerID) - amount);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static double getCustomerBalance(int id) {

        double balance = 0.0;
        var sqlSelect = "SELECT currentBal FROM customers WHERE customerNo=" +id+";";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                System.out.printf("%-20s%n",
                        rs.getDouble("currentBal")
                );
            }
            balance = rs.getDouble("currentBal");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return balance;
    }
	}

