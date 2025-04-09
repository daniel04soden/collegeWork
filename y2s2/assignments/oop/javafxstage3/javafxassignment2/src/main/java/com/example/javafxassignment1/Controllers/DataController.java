package com.example.javafxassignment1.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DataController
 */
public class DataController {
    private static Connection connection;

    public DataController() {
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
           createConn();
        }
        return connection;
    }

    private static void createConn() throws SQLException{

        try (var conn = DriverManager.getConnection("jdbc:sqlite:shop.db")) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable(String blob, int id){
        try (var conn = DriverManager.getConnection("jdbc:sqlite:shop.db");
             var stmt = conn.createStatement()) {
            // create a new table

            String sqlStmt = "CREATE TABLE IF NOT EXISTS" + blob + "(`name` varchar(50)),"
                + "`object`BLOB;";

            stmt.execute(sqlStmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
