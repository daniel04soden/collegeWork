package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Customer extends Agent{

    // Fields

    public String username;
    public int age;
    double currentBal;

    // Constructors

    public Customer(int customerID, String _username, int _age,double _currentBal){
        super(customerID);
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

    // Extra functionality
    public static boolean checkAge(int _age){
        return _age >= 18;
    }

    public void addMoneyToAccount(double extraToBal){
        this.currentBal += extraToBal;
    }
		
		public void takeMoneyFromAcc(double amount){
        var sql = "UPDATE customers SET currentBal = ? WHERE customerID=" + this.getId() +";";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
            // set the parameters
            pstmt.setDouble(1,this.currentBal - amount);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
	
	}

