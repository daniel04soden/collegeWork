# Daniel Soden A03 Project - A Java Application for Managing a Store

# Computer shop

#### Project requirements checklist

## OOP Concepts

- [ ] Primitive and Reference Variables.
- [ ] Classes and Objects.
- [ ] Encapsulation.
- [ ] Aggregation.
- [ ] Inheritance.
- [ ] Class Hierarchy.
- [ ] Static Polymorphism (overloading).
- [ ] Dynamic Polymorphism (overwriting).
- [ ] Abstract Class.
- [ ] Interface.
- [ ] User and Developer Isolation.
- [ ] Up-casting.
- [ ] Static Fields and Methods.
- [ ] Final Fields, Methods and Classes.
- [ ] Data Structures.
- [ ] Java Generics.
- [ ] Down-casting.
- [ ] Exception Handling.
- [ ] File Reading and Writing.
- [ ] Default Constructor and Copy Constructor

## Description of store/Main Functionality

- This computer shop functions like any other shop allowing users to view items, purchase items and return items.
- Before making any kind of purchases the user must register an account.
- In this project we assume each user has a £100 but can add more as time goes on (ATM Maybe?)
- Items: Laptops, Desktops, Spare parts
- Classes Hierarchy/Abstraction - Laptop -> Lenovo/Hp laptop -> Particular Generation/Models ??
-                     - Desktop -> Gaming/Workstation Desktop?
-                     - Parts -> Memory, Storage
- Reading/Writing from files
- Main ideas
    - SQLite - As a way of storing users and orders
    - Txt For receipts

- Data structures:
    - Arrays
    - Generics maybe
    - Custom datatypes?

- Interfaces:
    - Would work well for the object parts

- Exception Handling:
    - Exceptions for database types (will be mostly handled within itself)
    - Exceptions for items out of stock? (Pre defined stock on new run potentially)


## SQLite Planning

- Normalized for the most part (Not too stressed about things as long as it works for orders)
- Need to find driver: ()

- Final fields, methods and classes:
    - Final fields at the end of abstracted classes


## UML Diagrams

## Test cases
