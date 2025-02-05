//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package com.example.javafxassignment1ds;


//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.DriverManager;
import java.sql.SQLException;

//--------------------------------------------------
//
//	CLASS Database
//
//--------------------------------------------------
/**
 * This class models A Database connection<br>.
 */
public class Database {
    //---------------------------------------
    //	Fields
    //---------------------------------------

    public static String url = "jdbc:sqlite:/shop.db"; // Url created based on linux ext4 fs, Windows users

    //---------------------------------------
    //	Constructor
    //---------------------------------------

    /*
     * This Constructor takes in no attributes and instead just assigns the url to the current file url
     * */
    public Database(){
        url = "jdbc:sqlite:/home/daniels/collegeWork/y2s2/assignments/JavaFXAssignment1DS/src/main" +
                "/java/com/example/javafxassignment1ds/shop.db"; // Constructor present for OOP Sake
    }

    //---------------------------------------
    //	EXTRA METHODS
    //---------------------------------------

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

}
