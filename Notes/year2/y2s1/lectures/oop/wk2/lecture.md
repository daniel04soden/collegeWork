# OOP fundamentals

## What is OOP

- It is a programming paradigm where data and operations can be defined
  together as objects.
- It allows us to reuse these objects within the same or other
  programinng.
- We express to the computer the prblem we want to solve by modeling all
  concepts by objects.

## Pillars of OOP

- Polymorphism
- Abstraction
- Inheritance
- Encapsulation

### Examples:

- Ball, method being bounced, attriubutes/variables being colours
- Coffee, method being drank or poured, attriubutes/variables being
  flavour
- Car, method being driving, attriubutes/variables being model/make

## OOP File Structure:

- One MyMain file

- Various object files for each class, one file for each class.

  ``` java

  // Sample of main to object interpretation
  import otherFile.myFunction
  public class Main {
      public static void Main(String[] args){
       System.out.println(myFunction(myParam))
      }
  }


  ```

### 4 Parts of an object

- Objects:
  1.  Paramters/fields
      - Variables about the class itself

        ``` java
        public class drink{
            private boolean type; // This can only be accessed within this file
            public int sugarSpoons; // This can be accessed anywhere

        }

        ```
  2.  Constructors
      - Formal definition: In class-based, object-oriented programming,
        a constructor is a special type of function called to create an
        object. It prepares the new object for use, often accepting
        arguments that the constructor uses to set required member
        variables.

``` java
public Drink(boolean t, ints){
this.type = t;
this.sugarSpoons = s;
}

public boolean getType(){
    return this.type;
}
   public class Main {
       public static void Main(String[] args){
           Drink tea = new Drink(true,2);
           Drink cofee = new Drink(false,0);
       }
   }

```

1.  Alter/modify:

2.  Extra functionality
