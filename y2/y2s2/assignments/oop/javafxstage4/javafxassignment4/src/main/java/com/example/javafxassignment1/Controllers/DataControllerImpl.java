package com.example.javafxassignment1.Controllers;

import com.example.javafxassignment1.Models.Customer;
import com.example.javafxassignment1.Models.Product;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
public class DataControllerImpl implements DataController,Serializable{
    @Serial
    private static final long serialVersionUID = 1;
    public static String url = "jdbc:sqlite:src/main/java/com/example/javafxassignment1/database/shop.db";
    private static final DataControllerImpl dbController;

    private static Connection conn;

    static {
        try {
            dbController = new DataControllerImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //---------------------------------------
    //	Constructor
    //---------------------------------------

    private DataControllerImpl() throws SQLException {
        conn = createConn();
    }

    private Connection createConn() throws SQLException {
        return DriverManager.getConnection(url);
    }

    //---------------------------------------
    //	createCustomerTable
    //---------------------------------------

    public void createCustomerTable() {
        try (var stmt = getConn().createStatement()) { // Use the existing connection
            String sqlStmt= """
                    CREATE TABLE IF NOT EXISTS Customer(
                           customerID INTEGER PRIMARY KEY, -- Autoincrement automatically via og id situation
                           email VARCHAR(25),
                           name VARCHAR(30),
                           age INTEGER,
                           amount REAL
                       );
                   """;
            stmt.execute(sqlStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomer(int customerID) {
        String sqlStmt = """
        SELECT customerID, email, name, age, amount
        FROM Customer
        WHERE customerID = ?;
    """;

        try (var pstmt = getConn().prepareStatement(sqlStmt)) { // Use the existing connection
            pstmt.setInt(1, customerID);

            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("customerID");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    double amount = rs.getDouble("amount");

                    return new Customer.CustomerBuilder(id, name, age, amount)
                            .email(email)
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCustomer(int customerID) {
        String sqlStmt = "DELETE FROM Customer WHERE customerID = ?";
        try (var stmt = getConn().prepareStatement(sqlStmt)) { // Use the existing connection
            stmt.setInt(1,customerID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCustomer(Customer c) {
        String sql = "INSERT INTO Customer(customerID,email, name, age, amount) VALUES (?,?, ?, ?, ?)";
        try (var pstmt = getConn().prepareStatement(sql)) { // Use the existing connection
            pstmt.setInt(1, c.getId());
            pstmt.setString(2, c.getEmail());
            pstmt.setString(3, c.getName());
            pstmt.setInt(4, c.getAge());
            pstmt.setDouble(5, c.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer updateCustomer(int customerID,String name,int age,String email, double balance) {
        String sql = """
        UPDATE Customer
        SET email = ?,
            name = ?,
            age = ?,
            amount = ?
        WHERE customerID = ?;
    """;

        try (var pstmt = getConn().prepareStatement(sql)) { // Use the existing connection
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setDouble(4, balance);
            pstmt.setInt(5, customerID);
            pstmt.executeUpdate();
            return new Customer
                    .CustomerBuilder(customerID,name,age,balance)
                    .email(email)
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateBalance(int customerID, double balance) {
        String sql = """
        UPDATE Customer
        SET amount = ?
        WHERE customerID = ?;
    """;

        try (var pstmt = getConn().prepareStatement(sql)) { // Use the existing connection
            pstmt.setDouble(1, balance);
            pstmt.setInt(2, customerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataControllerImpl getDbController() {
        return dbController;
    }

    public Connection getConn() throws SQLException {
        if (conn==null){
            conn = createConn();
        }
        return conn;
    }
    @Override
    public ArrayList<Customer>getAllCustomers(){
            ArrayList<Customer> databaseCustomers = new ArrayList<>();
                String sqlStmt = """
        SELECT customerID, email, name, age, amount
        FROM Customer;
    """;

        try (var pstmt = getConn().prepareStatement(sqlStmt)) { // Use the existing connection

            try (var rs = pstmt.executeQuery()) {
                while (rs.next()){
                    int id = rs.getInt("customerID");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    double amount = rs.getDouble("amount");
                    Customer c = new Customer.CustomerBuilder(id, name, age, amount)
                            .email(email)
                            .build();
                   databaseCustomers.add(c); 
                }
                return databaseCustomers;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Customer>(); // If failure
        }
    }
    
    //---------------------------------------
    //	createProductTable
    //---------------------------------------

    public void createProductTable() {
        try (var stmt = getConn().createStatement()) { // Use the existing connection
            String sqlStmt= """
                    CREATE TABLE IF NOT EXISTS Product(
                           productID INTEGER PRIMARY KEY, -- Autoincrement automatically via og id situation
                           name VARCHAR(25),
                           stock INTEGER,
                           price REAL
                       );
                   """;
            stmt.execute(sqlStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> databaseProducts = new ArrayList<>();
        String sqlStmt = """
        SELECT productID,name, stock, price
        FROM Product;
    """;

        try (var pstmt = getConn().prepareStatement(sqlStmt)) {

            try (var rs = pstmt.executeQuery()) {
                while (rs.next()){
                    int id = rs.getInt("productID");
                    String name = rs.getString("name");
                    int stock = rs.getInt("stock");
                    double price = rs.getDouble("price");
                    Product p = new Product
                            .ProductBuilder(id,name,price)
                            .stock(stock)
                            .inStock()
                            .build();
                    databaseProducts.add(p);
                }
                return databaseProducts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Product>(); // If failure
        }
    }

    /**
     * @param productID
     * @return
     */
    @Override
    public Product getProduct(int productID) {
        String sqlStmt = """
        SELECT productID, name, stock, inStock, price
        FROM Product
        WHERE productID = ?;
    """;

        try (var pstmt = getConn().prepareStatement(sqlStmt)) { // Use the existing connection
            pstmt.setInt(1, productID);

            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("productID");
                    String name = rs.getString("name");
                    int stock = rs.getInt("stock");
                    double price = rs.getDouble("price");

                    return new Product
                            .ProductBuilder(id,name,price)
                            .stock(stock)
                            .inStock()
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param productID
     */
    @Override
    public void deleteProduct(int productID) {
        String sqlStmt = "DELETE FROM Product WHERE productID = ?";
        try (var stmt = getConn().prepareStatement(sqlStmt)) { // Use the existing connection
            stmt.setInt(1,productID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param p - Product object to save
     */
    @Override
    public void saveProduct(Product p) {
        String sql = "INSERT INTO Product(productID,name,stock,price) VALUES (?,?,?,?)";
        try (var pstmt = getConn().prepareStatement(sql)) { // Use the existing connection
            pstmt.setInt(1, p.getId());
            pstmt.setString(2, p.getName());
            pstmt.setInt(3, p.getStock());
            pstmt.setDouble(4, p.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param productID
     * @param name
     * @param stock
     * @param price
     * @param inStock
     * @return
     */
    @Override
    public Product updateProduct(int productID, String name, int stock, double price) {
        String sql = """
        UPDATE Product
        SET name = ?,
            stock = ?,
            price = ?,
        WHERE productID = ?;
    """;

        try (var pstmt = getConn().prepareStatement(sql)) { // Use the existing connection
            pstmt.setString(1,name);
            pstmt.setInt(2,stock);
            pstmt.setDouble(3,price);
            pstmt.setInt(4,productID);
            return new Product
                .ProductBuilder(productID,name,price)
                .stock(stock)
                .inStock()
                .build();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateStock(int productID, int stock) {
        String sql = """
        UPDATE Product
        SET stock = ?
        WHERE productID = ?;
    """;

        try (var pstmt = getConn().prepareStatement(sql)) { // Use the existing connection
            pstmt.setInt(1, stock);
            pstmt.setInt(2, productID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initTables(){
        createCustomerTable();
        createProductTable();
    }
}
