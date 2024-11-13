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

# Constructors

- What is a constructor in Object oriented programming?
	- Constructors are special type of functions called to create objects.
- Types of constructors:
	- Default constructor: A constructor which is implicitly created by the system if no other constructors are defined
	- Regular Constructors: Explicitly defined by us when creating the clsas, we can create as many constructors as we want, as long as they have different signatures (numbers/types of input parameters).
	- Copy constructors: a special constructor that creates a new object based on an existing one. It crease a new object by cloning the existing one (that is, by copying all its fields).
	``` java
	public Myconcept(Myconcept Myconcept_){...;}
	```

### Default constructor 
- If a java class doesn't include a constructor, the system will create an implicit one with no arguments ie:

```java 
	public class myConcept(){
	private int conceptField;
	}

	public myConcept(){}
```

- Therefore any other class will be able to create objects by using:

```java
Myconcept newObject = new Myconcept();
```

- The default constructor will initialise any:
	- Reference variable field (green arrow) as null.
	- Primitive variable field to a fixed value
	- E.g.: An int wold be 0 and a boolean would be false

### Copy constructor
- Shallow Copy -> Just simply copying all aspects of the premade object 
	```java
	Car copiedHonda = honda;
```

- Deep Copy -> this means creating a new object, make the new green arrow to point to it recursively copy all the internal fields of the object.

```java 
	   Car deepcopyHonda = new Car(             honda.name, new ArrayList<(honda.colors));   
```

# Inheritance

## What is inheritance?

- A mechanism in which one object acquires all the properties and behaviours of a parent object.
- This allows software developers to create and organise reusable classes.

- This makes it possible to create new classes built upon existing classes

- More over these new classes can add additional fields and methods, to provide a more specific functionality.
- Programmers can also modify existing methods and fields.

### Why use Inheritance ?

- All in all we can say inheritance allows for the reuse of software.
- By using existing software 


# Preview of mcq 

- 12 short questions

1. Each question gives you a java program and you determine the output:

1. error
2. sample output
3. sample output 2

- Covers:
1. Encapsulation
2. Polymorphism
3. Inheritance
4. Abstraction