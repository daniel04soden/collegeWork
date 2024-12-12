package org.example;


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
    public static String url = "jdbc:sqlite:./database/shop.db"; // Url created based on linux ext4 fs, Windows uses \

    //---------------------------------------
    //	Constructor
    //---------------------------------------

    public Database(){
       url = "jdbc:sqlite:./database/shop.db"; // Constructor present for OOP Sake
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