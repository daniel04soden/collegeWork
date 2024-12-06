package org.example;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public final class ShopImp implements Shop{
    // Fields

    // Null


   // Constructors

   public ShopImp(){

       // Initialising the customer and computer databases rather than keeping them in a list

       Database.createTable(Database.url,
               "CREATE TABLE customers IF NOT EXISTS(" +
                            "customerID INTEGER PRIMARY KEY AUTOINCREMENT," +
                       "username text," +
                       "age INTEGER," +
                       "currentBal REAL," +
                       ");"
       ); // Handles creating a Customer table

       Database.createTable(Database.url,
               "CREATE TABLE computers IF NOT EXISTS(" +
                       "productID INTEGER PRIMARY KEY AUTOINCREMENT," +
                       "price REAL," +
                       "stock INTEGER," +
                       "amountOnOrder INTEGER," +
                       "ram INTEGER," +
                       "storage INTEGER," +
                       "CPU TEXT" +
                       "GPU TEXT" +
                       ");"
       );
       Database.createTable(Database.url,
               "CREATE TABLE orders IF NOT EXISTS(" +
                       "orderID INTEGER PRIMARY KEY AUTOINCREMENT," +
                       "customerID INTEGER," +
                       "productID INTEGER," +
                       "cost REAL," +
                       "FOREIGN KEY (customerID) REFERENCES customer(customerID)," +
                       "FOREIGN KEY (productID) REFERENCES computer(productID)" +
                       ");"
       );
   }

    // Methods

    @Override
    public void listItems() {
        var sqlSelect = "SELECT productID,productName FROM computers";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                // TODO Add in sql selects
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void orderStock(int productID) {

    }

    @Override
    public Customer addCustomer(int customerID,String name, int age, double currentBal) {
        Customer brandNewCustomer = new Customer(customerID,name,age,currentBal);
        boolean correctAge = brandNewCustomer.checkAge(brandNewCustomer.age);

        if (!correctAge){
            System.out.println("You must be over 18 to be a customer at this store");
						return null;
        }else{
						return brandNewCustomer;
        }

    }

    @Override
    public void removeCustomer(int id, String name) {

    }

    @Override
    public void displayCustomerInfo(int id) {
            var sqlSelect = "SELECT * FROM customers";


        try (var conn = DriverManager.getConnection(Database.url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                System.out.printf("%-5s%-20s%-4s%-20s%n",
                        rs.getInt("customerID"),
                        rs.getString("username"),
                        rs.getInt("age"),
                        rs.getDouble("currentBal")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addMoneyToCustAccount(int amount) {

    }

    @Override
    public int purchaseItem(int customerID, int productID, double amountGiven) {
        return 0;
    }

    @Override
    public int returnItem(int customerID, int productID, double itemPrice) {
        return 0;
    }
}
