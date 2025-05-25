# Classes and Objects

- **Class** is the blueprint of which an object is created.
- **Objects** are the instantiation of a class.
- **Attributes** info that describes the class - fields.
- **State**: Describes the values and relationships at a point in time.
- **Methods**: The behaviour of a class
- **Messages**: Info sent to an object to trigger a method (procedure call)
![[Pasted image 20250506143436.png]]

![[Pasted image 20250506143458.png]]

# OO Pillars
## Characteristics of OO Systems

### Encapsulation and Information Hiding 
- Encapsulation combination of process and data
- Info hiding - functionality
- Encapsulation takes in real life concepts and translates them into models/representations of themselves in UML/Code
### Inheritance 
- General classes are created (super classes)
- Sub classes can inherit data and methods from a superclass
- Parent classes pass down fields and methods to their child classes
### Polymorphism 
- Polymorphism: the same message can have different meanings
- Dynamic binding: the type of object is not deteermined until run time
- Contrasts with static binding wherein the object is constructed one way at compile time and never changes
### Abstraction
- Something non-concrete
- To create an interface or an abstract class which is fundamentally non-concrete ie we cannot.

- This means we cannot create an actual instance of an interface or abstract class - we can only extend from/implement
- An abstract class can have its own implementations so acts as an object which can be modified from whereas an interface is just the blueprint and you must implement those methods

- Interface code:
```java
import java.io.*;
interface testInterface {
    final int a = 10;
    void display();
}

class TestClass implements testInterface {

    public void display(){ 
      System.out.println("Geek"); 
    }
}
```
- Abstract class code:
```java
abstract class Animal {
  // Abstract method (does not have a body)
  public abstract void animalSound();
  // Regular method
  public void sleep() {
    System.out.println("Zzz");
  }
}

// Subclass (inherit from Animal)
class Pig extends Animal {
  public void animalSound() {
    // The body of animalSound() is provided here
    System.out.println("The pig says: wee wee");
  }
}
```
# OO Systems Analysis and design
- Use case driven:
	- Use cases define the behaviour of a system
	- Each use case focuses on one business process
- Architecture centric
	- Functional (external) - view focuses on users perspective
	- Static  (Structural)  - view focuses on attributes methods classes & relationships
	- Dynamic (Behavioural) - view focuses on messages between classes and resulting behaviours.
- Iterative and incremental
	- Undergoes continuous testing and refinement
	- Test driven development
	- As code evolves, different tests are found and incremented on
# Benefits of OOAD
- Breaks complex sys into smaller manageable modules
- Allows us to then work on those modules individually.