package com.example.javafxassignment1.Models;

public class Customer {
    private final int id;
    private String name;
    private String email;
    private int age;
    private double balance;

    public Customer(int _id,String _name,String _email, int _age, double _balance){
       this.id = _id;
       this.name = _name;
        this.email = _email;
        this.age = _age;
        this.balance = _balance;
    }

    // Getters

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }
    public int getAge(){
        return this.age;
    }

   public double getBalance() {
        return  this.balance;
   }

    // Setters
    void setName(String newName){
        this.name = newName;
    }

    void setEmail(String newEmail){
        String emailRegex = "/^\\S+@\\S+\\.\\S+$/";
        if (newEmail.matches(emailRegex)){
            this.email = newEmail;
        }else{
            System.out.println("Invalid error");
            // Display GUI error
        }
    }
    public void setBalance(double newBalance){
        this.balance = newBalance;
    }

    public void setAge(int _age){
        this.age = _age;
    }
    // Omit idSetter, non-editable value

    // Extra functionality
    @Override
    public String toString(){
        return this.name+":" + this.id  + this.email + "\n";
    }
}
