package org.example;

public class Customer extends Agent{
    public String username;
    public int age;

    public Customer(int customerID, String _username, int _age){
        super(customerID);
        this.username = _username;
        if (checkAge(_age)){
           this.age = _age;
        }else{
            throw new RuntimeException("Our user is too young to be a customer");
        }
    }

    // Getters and setters

    private boolean checkAge(int _age){
        return _age >= 18;
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
}
