package org.example;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class ShopImp implements Shop{
    // Fields

    // Null

   // Constructors

   public ShopImp(){

       // Initialising the customer and computer databases rather than keeping them in a list

       Database.createTable(Database.url,
               "CREATE TABLE customers IF NOT EXISTS(" +
                            "customerID INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                       "username text," +
                       "age INTEGER," +
                       "currentBal REAL," +
                       ");"
       ); // Handles creating a Customer table

       Database.createTable(Database.url,
               "CREATE TABLE computers IF NOT EXISTS(" +
                       "productID INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
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
                System.out.printf(
                        "%-10s%-20s%n", // Formatting column
                        rs.getInt("productID"),
                        rs.getString("productName")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void orderStock(int productID) {

    }

    public Customer addCustomer(int customerID,String name, int age, double currentBal) {
        Customer brandNewCustomer = new Customer(customerID,name,age,currentBal);
        boolean correctAge = brandNewCustomer.checkAge(brandNewCustomer.age);

        if (!correctAge){
            System.out.println("You must be over 18 to be a customer at this store");
						return null;
        }else{

            String sqlStmt = "INSERT INTO customers(" +
                    "customerID,username,age,currentBal)" +
                    "VALUES(" + brandNewCustomer.getId() +
                    brandNewCustomer.username + brandNewCustomer.age + brandNewCustomer.currentBal +
                    ")";

            try(var conn = DriverManager.getConnection(Database.url);
                var prepStmt = conn.prepareStatement(sqlStmt)){
                prepStmt.setInt(1,brandNewCustomer.getId());
                prepStmt.setString(2,brandNewCustomer.username);
                prepStmt.setInt(3,brandNewCustomer.age);
                prepStmt.setDouble(4,brandNewCustomer.currentBal);
                prepStmt.executeUpdate();

            } catch (SQLException e){
                System.err.println(e.getMessage());
            }
            return brandNewCustomer; // Return statement - Main functionality relates to the sql insertion
        }

    }

    @Override
    public void removeCustomer(Customer c, String name) {
       String confirm = "YES I WANT TO PROCEED TO DELETE MY THIS CUSTOMER";
       String deletePrompt = Main.scanString("Would you like to delete your user?",255,1);
       System.out.println("To proceed please enter:   " + confirm);
       if (deletePrompt.equals(confirm)){
           var sql = "DELETE FROM customer WHERE id = ?";
           var id = c.getId();

           try (var conn = DriverManager.getConnection(Database.url);
                var prepStmt = conn.prepareStatement(sql)) {

               prepStmt.setInt(1, id);

               // execute the delete statement
               prepStmt.executeUpdate();

           } catch (SQLException e) {
               System.err.println(e.getMessage());
           }
           c = null;
       }
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

    public void addMoneyToCustAccount(Customer c) {
       int id = Main.scanInt("Enter your user id to add money to your account:",9,9);
        double amount = Main.scanDouble("How much do you want to add?",100,1);
       c.addMoneyToAccount(id,amount);

       var sql = "UPDATE customers SET currentBal = ? WHERE customerID = ?";

        try (var conn = DriverManager.getConnection(Database.url);
             var prepstmt = conn.prepareStatement(sql)) {
            // set the parameters
            prepstmt.setDouble(1, amount);
            prepstmt.setInt(2, c.getId());
            // update
            prepstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());


            System.out.println("You have successfully added €" + amount + " to your account and now have €" + c.currentBal);
    }

    @Override
    public int purchaseItem(int customerID, product p, double amountGiven) {
        return 0;
    }

    @Override
    public int returnItem(int customerID, int productID, double itemPrice) {
        return 0;
    }
}