//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package org.example;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;


//--------------------------------------------------
//
//	CLASS Order
//
//--------------------------------------------------

/**
 * This class models an Order from the Shop<br>.
 */
public class Order { // This class models an order for a computer with various ids for identification

    //---------------------------------------
    //	Fields
    //---------------------------------------

    private int orderID; // Generated order id for purchase
    private final int customerID; // Customer who made this order
    private final int productID; // Product of which they bought
    public final double cost; // Cost of entire order

    //---------------------------------------
    //	Constructor
    //---------------------------------------

    /**
     * This constructor creates a model of a user and inserts it into the db
     * @param _customerID - given customer id for customer
     * @param _productID - given product id of which the customer is ordering
     * @param _cost - cost of the product customer is buying
     */
    public Order(int _customerID, int _productID, double _cost){
        // Inserting order data into sqlite orders table
        String sqlStmt = "INSERT INTO orders(customerNo, productNo, cost) VALUES(?, ?, ?)";

        try (var conn = DriverManager.getConnection(Database.url);
             var prepStmt = conn.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS)) {
            prepStmt.setInt(1, _customerID);
            prepStmt.setInt(2, _productID);
            prepStmt.setDouble(3, _cost);
            prepStmt.executeUpdate();

            // Retrieve the generated orderNo
            try (var rs = prepStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    this.orderID = rs.getInt(1); // Assign to class field
                } else {
                    System.err.println("Failed to retrieve generated orderID");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // Initializing other constructor values
        this.customerID = _customerID;
        this.productID = _productID;
        this.cost = _cost;
    }

    //---------------------------------------
    //	GET METHODS
    //---------------------------------------

    //---------------------------------------
    //	getCost
    //---------------------------------------

    /**
     * Given a concrete order, this method returns our cost
     * @return cost - Cost of entire order
     */
    public double getCost() {
        return cost;
    }

    //---------------------------------------
    //	getCustomerID
    //---------------------------------------

    /**
     * Given a concrete order this method returns the customer who bought the product's id
     * @return customerID - Gives back customerid from order
     */
    public int getCustomerID() {
        return customerID;
    }

    //---------------------------------------
    //	getProductID
    //---------------------------------------

    /**
     * Given a concrete product, this method returns their productID
     * @return productID - Gives back productID from order
     */
			public int getProductID() {
				return productID;
		}


    //---------------------------------------
    //	EXTRA METHODS
    //---------------------------------------

    //---------------------------------------
    //	toString
    //---------------------------------------

    /**
     * Grabs class data into order to convert it into a digestible string
     * This is mainly used so far in making receipts
     * @return String - Returns string with order details
     */
    @Override
    public String toString() {
        return "Order{" +
                "customerID=" + customerID +
                ", productID=" + productID +
                ", cost=" + cost +
                '}';
    }

}
