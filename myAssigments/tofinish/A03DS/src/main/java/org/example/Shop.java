//--------------------------------------------------
//	PACKAGES
//--------------------------------------------------
package org.example;

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

//--------------------------------------------------
//
//	INTERFACE Shop
//
//--------------------------------------------------
/*
* This is the primary interface for the java computer shop
* application
* */
public interface Shop {

    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * Given a concrete name age and balance, a customer
     * can be created via sqlite
     * @param name - String name given by the customer
     * @param age - int age of the customer
     * @param currentBal - double current balance for the customer
     */
    void addCustomer(String name, int age, double currentBal);

    //---------------------------------------
    //	removeCustomer
    //---------------------------------------

    /**
     * Given a concrete name and id, a customer can be fully removed
     * from the sqlite database
     * @param id - int unqiue id customer
     * @param name - String name of the customer
     */
    void removeCustomer(int id,String name);

    //---------------------------------------
    //	displayCustomerInfo
    //---------------------------------------

    /**
     * Given a concrete id, all relevant details relating to a customer
     * can be listed
     * @param id - int unqiue id customer to find display info
     */
    void displayCustomerInfo(int id);

    //---------------------------------------
    //	addMoneytoCustAccount
    //---------------------------------------

    /**
     * Given an id and amount of money to add
     * a customers balance will be updated with this amount, like an atm
     * @param id - int unique id of customer
     * @param amount - double amount of money to be added to customer account
     */
    void addMoneyToCustAccount(int id,double amount);

    // Product methods

    //---------------------------------------
    //	purchaseItem
    //---------------------------------------

    /**
     * Given a concrete id and productNo a customer
     * is able to purchase an item which both adjusts their
     * balance and our stock levels
     * @param id - int unique id of the customer
     * @param productNo - product no of which customer will buy
     */
    void purchaseItem(int productNo, int id);
    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * @param name - String name given by the customer
     * @param age - int age of the customer
     * @param currentBal - current balance for the customer
     */
    void listItems();
    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * @param name - String name given by the customer
     * @param age - int age of the customer
     * @param currentBal - current balance for the customer
     */
    void listCurrentStock();


    // Order Methods

    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * @param name - String name given by the customer
     * @param age - int age of the customer
     * @param currentBal - current balance for the customer
     */
    void listRecentOrders();
    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * @param name - String name given by the customer
     * @param age - int age of the customer
     * @param currentBal - current balance for the customer
     */
    void saveReceipt(Order o);

}
