# Encapsulation

- Hide things that should be hidden.
- Key feature of OOP;
  - Data hiding
  - Controlled access - Getters,setters etc
  - Increased flexibility
  - Improved Security \# Access Modifiers
- Includes public private protected.
- Private is the most secure, it allows us to keep everything hidden yet
  still gives the option of adding getters and setters.
- Applied to fields,methods classes and interfaces
- Modifier table

  Modifier    Class   Package   Subclass   World/Main
  ----------- ------- --------- ---------- ------------
  Public      Y       Y         Y          Y
  Private                                  
  Protected   Y       Y         Y          N
  Default                                  

- Tip: Always start with private and use other modifiers as needs be.

- Fields should be declared as private primarily

- We can access such private fields with our getters and setters.

- Best practice is to name them getFieldName, setFieldName.

- Getters and setters must be public - useless otherwise as if the
  fields were just public we can set and get anyway.

- Variables and methods must have their return type declared before
  naming them

- What does the void return type return?

- Getters and setters can be used for object relational mapping - Used
  in project. SQLite again kekw.

- Maybe nosql work better for our project implementation

## Why is encapsulation important

- Data integrity
- Modularity
- Readability
- Security especially

# Classes

## What is in a class?

- A constructor.
- Instance variables.
- Methods.
- What else can it hold?

### Other classes - Nested Classes

- Or inner classes

- Nested class syntax

``` java
public class EnclosingClass{
    ...
    public class InnerClass{
        ...

    }
}
```

- Can be used to crate an instance of the inner object we use the
  follower code

``` java
EnclosingClass.InnerClass innerObject = new EnclosingClass.InnerClass('fields');
```

- Logically groups classes

- Instance of the inner class are localised within the enclosing class

- Instances of the inner class have access to the private members of the
  enclosing class.

1.  Static inner classes

    - Static inner classes are not storngly associated with the outer
      class object
    - Which means that static inner clases can be made wihtout the
      instance of the outer classs

2.  Anonymous inner classes

    - Obeys rules of inner clsasses
    - Creates one off objects
    - Uses special syntax

    ``` java
    interface age{
    int x  = 21;
    void getAge()
    };


    // nextFile

        Age obj1 = new Age{
            @Override public void getAge()
            ... 

        }
    ```
