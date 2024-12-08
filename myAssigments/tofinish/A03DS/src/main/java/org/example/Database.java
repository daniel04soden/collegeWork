package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static String url = "jdbc:sqlite:./database/shop.db";

    public static void connect(String url) {
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("Successful connection");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void createTable(String url, String sqlStatement) {
        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}