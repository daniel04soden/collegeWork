package org.example;

import java.util.ArrayList;

public final class ShopImp implements Shop{
    // Fields

    // Null


   // Constructors

   public Shop(){

       // Initialising the customer and computer databases rather than keeping them in a list

       Database.createTable(Database.url,
               "CREATE TABLE customers IF NOT EXISTS(" +
                            "customerId INTEGER PRIMARY KEY AUTOINCREMENT," +
                       "username text," +
                       "age INTEGER," +
                       "currentBal INTEGER," +
                       ");"
       ); // Handles creating a Customer table

       Database.createTable(Database.url,
               "CREATE TABLE computers IF NOT EXISTS(" +
                       "productId INTEGER PRIMARY KEY AUTOINCREMENT," +
                       "price INTEGER," +
                       "stock INTEGER," +
                       "amountOnOrder INTEGER," +
                       "ram INTEGER" +
                       "storage INTEGER" +
                       ")"
       );
   }

    // Methods

    @Override
    public String listItems() {
        return "";
    }

    @Override
    public int addCustomer(String name, int age, double currentBal) {
        Customer brandNew = new Customer(newId,name,age,currentBal);

        return newId;
    }

    @Override
    public void removeCustomer(int id, String name) {

    }
}
