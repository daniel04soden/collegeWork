package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// TODO - re-eval ids system
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

    public void createTable() {
        try (var conn = getConn();
             var stmt = conn.createStatement()) {
            String sqlStmt= """
                    CREATE TABLE IF NOT EXISTS Customer(
                           customerID INTEGER PRIMARY KEY, -- Autoincrement automatically
                           email VARCHAR(25),
                           name VARCHAR(30),
                           age INTEGER,
                           amount REAL
                       );
                   """;
            stmt.execute(sqlStmt);
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
        try (var conn = getConn();
             var stmt = conn.createStatement()) {

            stmt.execute("");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @param customerID
     */
    @Override
    public void deleteCustomer(int customerID) {
        try (var conn = getConn();
             var stmt = conn.createStatement()) {
            // create a new table

            stmt.execute("");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param c
     */
    @Override
    public void saveCustomer(Customer c) {
        String sql = "INSERT INTO Customer(email, name, age, amount) VALUES (?, ?, ?, ?)";
        try (var conn = getConn();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getEmail());
            pstmt.setString(2, c.getName());
            pstmt.setInt(3, c.getAge());
            pstmt.setDouble(4, c.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param customerID
     * @return
     */
    @Override
    public Customer updateCustomer(int customerID,String name,int age,String email, double balance) {
        String sql = """
        UPDATE Customer
        SET email = ?,
            name = ?,
            age = ?,
            amount = ?
        WHERE customerID = ?;
    """;

        try (var conn = getConn();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setDouble(4, balance);
            pstmt.setInt(5, customerID);

            pstmt.executeUpdate();

            return new Customer
                    .CustomerBuilder(customerID,name,age,balance)
                    .email(email)
                    .build();
        } catch (SQLException e) {
           e.printStackTrace();
           return null;
        }
    }

    public void updateBalance(int customerID, double balance) {
        String sql = """
        UPDATE Customer
        SET amount = ?
        WHERE customerID = ?;
    """;

        try (var conn = getConn();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, balance);
            pstmt.setInt(2, customerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
