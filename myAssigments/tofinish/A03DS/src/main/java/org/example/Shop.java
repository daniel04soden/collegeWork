package org.example;

public interface Shop {

    // Customer Methods

    public Customer addCustomer(String name,int age, double currentBal);
    public void removeCustomer(int id,String name);
    public void displayCustomerInfo(int id);
    public void addMoneyToCustAccount(int id,double amount);

    // Product methods

    public void purchaseItem(int productNo, int id);
    public void listItems();
    public void listCurrentStock();

    // Order Methods

    public void listRecentOrders();
    public String saveReceipt(Order o); 

}
