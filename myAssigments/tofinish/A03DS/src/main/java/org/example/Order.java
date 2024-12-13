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
/*
* This class models an order for a computer <br>.
* */
public class Order {
    //---------------------------------------
    //	Fields
    //---------------------------------------

    private int orderID;
    private final int customerID;
    private final int productID;
    public final double cost;

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
     * @return cost - Cost of entire order
     */
    public double getCost() {
        return cost;
    }

    //---------------------------------------
    //	getCustomerID
    //---------------------------------------

    /**
     * @return customerID - Gives back customerid from order
     */
    public int getCustomerID() {
        return customerID;
    }

    //---------------------------------------
    //	getProductID
    //---------------------------------------

    /**
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
