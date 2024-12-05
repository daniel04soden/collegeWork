package org.example;

public interface Shop {

    // Customer Methods

    public int addCustomer(String name,int age, double currentBal);
    public void removeCustomer(int id,String name);
    public String displayCustomerInfo(int id);
    public void addMoneyToCustAccount(int amount);

    // Product methods

    public int purchaseItem(int customerID, int productID, double amountGiven);
    public int returnItem(int customerID, int productID, double itemPrice);
    public String listItems();

    // Stock Methods
    public void orderStock(int productID);
}
