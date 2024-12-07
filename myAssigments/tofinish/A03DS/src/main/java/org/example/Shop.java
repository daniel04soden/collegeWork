package org.example;

public interface Shop {

    // Customer Methods

    public Customer addCustomer(String name,int age, double currentBal);
    public void removeCustomer(int id,String name);
    public void displayCustomerInfo(int id);
    public void addMoneyToCustAccount(int amount);

    // Product methods

    public double purchaseItem(int customerID, int productID, double amountGiven);
    public void listItems();
    public void listItemIDs();

    // Order Methods

    public void listRecentOrders();
    public String saveReceipt(Order o);

}
