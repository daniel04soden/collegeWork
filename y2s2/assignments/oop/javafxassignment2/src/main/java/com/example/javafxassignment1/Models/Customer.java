package com.example.javafxassignment1.Models;

public class Customer extends Agent{
    private String email;
    private int age;
    private double balance;

    public Customer(int _id,String _name,String _email, int _age, double _balance){
        super(_id,_name);
        this.email = _email;
        this.age = _age;
        this.balance = _balance;
    }

    // Getters

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

    void setEmail(String newEmail){
        String emailRegex = "/^\\S+@\\S+\\.\\S+$/";
        if (newEmail.matches(emailRegex)){
            this.email = newEmail;
        }else{
            System.out.println("Invalid email");
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
    public String returnToDb(){
        return super.getId() + "," + super.getName()+ "," + this.age+ "," + this.email+ "," + this.balance;
    }
    @Override
    public String toString(){
        return super.getName();
    }
}
