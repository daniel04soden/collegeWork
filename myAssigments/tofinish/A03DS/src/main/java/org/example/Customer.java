package org.example;

public class Customer extends Agent{

    // Fields

    public String username;
    public int age;
    double currentBal;

    // Constructors

    public Customer(int customerID, String _username, int _age,double _currentBal){
        super(customerID);
        this.username = _username;
        this.currentBal = _currentBal;
        this.age = _age;
    }

    // Getters and setters


    public double getCurrentBal() {
        return currentBal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Extra functionality
    public boolean checkAge(int _age){
        return _age >= 18;
    }

    public void addMoneyToAccount(int id, double extraToBal){
        this.currentBal += extraToBal;
    }

}