package com.example.javafxassignment1.Controllers;

public interface CRUDController {
    public void add(int id, String name,int attr1, double attr2); // will refactor in time -

    void add(String name, String email, int age, double balance);

    // convenient given 4 attr only for both
                                                                    // attr 1 is age and stock
                                                                    // attr 2 is price and balance
    public void delete(int id, String permissionPass);
   public void load();
   public void save();
   public void print();
   //TODO consider rather than having that be in load ->
    // public void stringify();
}
