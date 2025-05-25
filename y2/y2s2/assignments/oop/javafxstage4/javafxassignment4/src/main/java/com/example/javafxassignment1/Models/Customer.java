package com.example.javafxassignment1.Models;

import java.io.*;

public class Customer implements Serializable,Comparable<Customer>{
	private String email;
	private int id;
	private String name;
	private int age;
	private double balance;

	@Serial
	private static final long serialVersionUID = 1;

	private Customer(CustomerBuilder builder){
		this.email = builder.email;
		this.name = builder.name;
		this.id = builder.id;
		this.balance = builder.balance;
		this.age = builder.age;
		}

	public static CustomerBuilder builder(int id_,String name_,int age_, double balance_){
		return new CustomerBuilder(id_,name_,age_,balance_);
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

	public void setEmail(String newEmail){
			this.email = newEmail;
	}

	public void setBalance(double newBalance){
		this.balance = newBalance;
	}

	public void setAge(int _age){
		this.age = _age;
	}
	// Omit idSetter, non-editable value

	// Extra functionality
	public String editableDisplay(){
		return "Customer:\n"
				+"Name:"+ getName() + "\n"
				+"Age:"+Integer.toString(getAge()) + "\n"
				+"Balance:"+ Double.toString(getBalance()) + "\n"
				+"Email:"+ getEmail();
	}

	@Override
	public String toString(){
		return getName();
	}

	@Override
	public int compareTo(Customer c) {
		return this.getName().compareTo(c.getName());
	}

	public int getId() {
		return id;
	}

	public static long getSerialVersionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Builder pattern
	public static class CustomerBuilder {
		private String email;
		private final int id;
		private final String name;
		private final int age;
		private final double balance;

		public CustomerBuilder(int id_,String name_,int age_, double balance_){
			this.id = id_;
			this.name = name_;
			this.age = age_;
			this.balance = balance_;
		}

		public CustomerBuilder email(String email){
			this.email = email;
			return this;
		}

		public Customer build(){
			return new Customer(this);
		}

	}
}


