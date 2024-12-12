package org.example;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;


public class Order {
    private int orderID;
    private final int customerID;
    private final int productID;
    public final double cost;

    public Order(int _customerID, int _productID, double _cost){
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
        this.customerID = _customerID;
        this.productID = _productID;
        this.cost = _cost;
    }
    // Getters
    public double getCost() {
        return cost;
    }

		public int getCustomerID() {
			return customerID;
		}

			public int getProductID() {
				return productID;
		}
		

    // Printing value to String
    @Override
    public String toString() {
        return "Order{" +
                "customerID=" + customerID +
                ", productID=" + productID +
                ", cost=" + cost +
                '}';
    }

}
