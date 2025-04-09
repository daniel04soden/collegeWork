package com.example.javafxassignment1.Controllers;
import java.sql.*;


/**
 * DataController
 */

public class DataController {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
       if (conn == null || conn.isClosed()) {
           createConnection();
       }
       return conn;
    }

    public static void createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";
        conn = DriverManager.getConnection(url, username, password);
    }



}
