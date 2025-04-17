package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataControllerImpl implements DataController{
    public static String url = "jdbc:sqlite:./database/shop.db"; // Url created based on linux ext4 fs, Windows uses
    private static final DataControllerImpl dbController;

    static {
        try {
            dbController = new DataControllerImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final Connection conn;

    //---------------------------------------
    //	Constructor
    //---------------------------------------

    private DataControllerImpl() throws SQLException {
        url = "jdbc:sqlite:src/main/java/com/example/javafxassignment1/database/shop.db";
        this.conn = createConn();
    }

    private Connection createConn() throws SQLException {
        return DriverManager.getConnection(url);
    }

    //---------------------------------------
    //	createTable
    //---------------------------------------
    /**
     * This method attempts to find it a user is in the usersList<br>
     * @param url - String referring to the url of the database.
     * @param sqlStatement - The sql statement being used to initialise this table
     */
    public static void createTable(String url, String sqlStatement) {
        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param customerID
     * @return
     */
    @Override
    public Customer getCustomer(int customerID) {
        return null;
    }

    /**
     * @param customerID
     */
    @Override
    public void deleteCustomer(int customerID) {

    }

    /**
     * @param c
     */
    @Override
    public void saveCustomer(Customer c) {

    }

    /**
     * @param customerID
     * @return
     */
    @Override
    public Customer updateCustomer(int customerID) {
        return null;
    }

    @Override
    public Connection establishConnection() throws SQLException {
       return DriverManager.getConnection(url);
    }

    public static DataControllerImpl getDbController() {
        return dbController;
    }

    public Connection getConn() {
        return conn;
    }
    public void verify(){
        try (var conn = getConn();
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(".tables;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
