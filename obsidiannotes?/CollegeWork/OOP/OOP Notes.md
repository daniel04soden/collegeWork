# Four Pillars Of Object Oriented Principles

1. Encapsulation
2. Inheritance
3. Polymorphism
4. Abstraction

## Encapsulation

- **Encapsulation** is the process of putting together a data entity with its main fields methods into a single unit.
- It isolates the concept being modelled from:
	- The real world
	- Any other relevant concepts


### Private Fields 

- By using private fields, encapsulation hides the data characteristics from anywhere else in the Java application that is not the proper class.
```java 

private int height; // Private field, can only be accessed here or by a get/set
public int age; // Public field, can be accessed in the main
```

- In encapsulation we fully depend on what the class drink gives us whether that be fields, methods or anything else, whether they are private or not we need to keep that into account.

## Get and set methods 

- By using public get and set methods,encapsulation allows to:
	- Specify the concrete level of accessibility we want
	- From anywhere outside the class
- To the objects fields.

- If we add a get method for a field this means other classes will be able to know certain information about particular fields.
- If we do not add a get method this means other classes won't be able to know that there is one field of mydrink that is a boolean nor get access to it.

## Aggregation

- When designing a Java application, it is okay to create an instance of one class as a reference within another class
- The same principles of data hiding an accessibility apply within this type of encapsulation. 
	- We will have as much knowledge and accessibility to the fields of MyConcepts as the one defined in the get and set methods of MyConcept
- It implies a parent child relationship in that a child can exist independently of the parent
- The life-cycles of each of the involved Objects are independent.
- 