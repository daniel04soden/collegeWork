package org.example;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.DriverManager;
import java.sql.SQLException;



public abstract class Product extends Agent{

    public double price;
    protected int currentStock;
    protected int amountOnOrder;


    public Product(int productId, double _price, int _currentStock, int _amountOnOrder){
        super(productId);
        this.price = _price;
        this.currentStock = _currentStock;
        this.amountOnOrder = _amountOnOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double _price) {
        this.price = _price;
    }

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

    // No setter for setting current stock, that will be implemented as follows:

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

    public abstract int getAmountOnOrder();

    public void setAmountOnOrder(int amountOnOrder) {
        this.amountOnOrder = amountOnOrder;
    }

}
