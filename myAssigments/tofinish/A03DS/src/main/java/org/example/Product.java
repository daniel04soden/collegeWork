//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package org.example;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.DriverManager;
import java.sql.SQLException;


//--------------------------------------------------
//
//	ABSTRACT CLASS Product
//
//--------------------------------------------------
/*
* This class models a General Product for the computer shop
* */
public abstract class Product extends Agent{

    //---------------------------------------
    //	Fields
    //---------------------------------------

    public double price;
    protected int currentStock;
    protected int amountOnOrder;


    //---------------------------------------
    //	Constructor
    //---------------------------------------

    /**
     * This constructor creates a model of a user and inserts it into the db
     * @param productId -unique identifier for the product
     * @param _price - Price charged for this product
     * @param _currentStock - Current stock in house of this product
     * @param _amountOnOrder - Amount more of this product on order to stock
     */
    public Product(int productId, double _price, int _currentStock, int _amountOnOrder){
        super(productId); // Calling Agent super class
        this.price = _price; // Assigning fields to current object
        this.currentStock = _currentStock;
        this.amountOnOrder = _amountOnOrder;
    }

    //---------------------------------------
    //	GET METHODS
    //---------------------------------------

    //---------------------------------------
    //	getPrice
    //---------------------------------------

    /**
     * @return cost - Price of defined Product
     */
    public double getPrice() {
        return price;
    }


    //---------------------------------------
    //	getCurrentStock
    //---------------------------------------

    /**
     * This method makes use of how its static by allowing a user to
     * search for any particular product with having to create a product
     * class explicitly
     * @param id - Given concrete id to find stock via select statment
     * @return stock - gives the stock stored in the sqlite db
     */
    public static int getCurrentStock(int id) {
        var sql = "SELECT stock FROM computers WHERE productNo = ?";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Set the product ID as the first parameter

            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock"); // Best case scenario
                } else { // Customer cannot be found
                    System.err.println("Product " + id + " not found.");
                    return 0;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); // SQLError
            return 0;
        }
    }

    //---------------------------------------
    //	getAmountOnOrder
    //---------------------------------------

    /**
     * This method checks the sqlite database to retrieve the current amount
     * on order
     * @return amountOnOrder - retrieves the amountOnOrder through sqlite
     */
    public abstract int getAmountOnOrder();


    //---------------------------------------
    //	SET METHODS
    //---------------------------------------


    //---------------------------------------
    //	setPrice
    //---------------------------------------
    /**
     * @param _price - takes in a new price to replace the original price of the object
     */
    public void setPrice(double _price) {
        this.price = _price;
    }

    //---------------------------------------
    //	setAmountOnOrder
    //---------------------------------------
    /**
     * @param _amountOnOrder - takes in a new amount on order and replaces the original amount
     */
    public void setAmountOnOrder(int _amountOnOrder) {
        this.amountOnOrder = _amountOnOrder;
    }

    // No setter for setting current stock, that will be implemented as follows:

    //---------------------------------------
    //	stockDecremented
    //---------------------------------------
    /**
     * Given a concrete id, the method decrements the current
     * stock by 1 for when a user purchases a prodcut in the sqlite
     * db
     * @param id - Given product id to decrement
     */
    public static void stockDecremented(int id) {
        var sql = "UPDATE computers SET stock = ? WHERE productNo = ?";

        try (var conn = DriverManager.getConnection(Database.url);
             var pstmt = conn.prepareStatement(sql)) {
                 int stock = getCurrentStock(id);
                 pstmt.setInt(1,stock-1);
                 pstmt.setInt(2,id);

            // Update the database
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


}
