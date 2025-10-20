The *relationship* between 2 objects is known as the *association*. Both
*composition* and *aggregation* are *associations* between 2 objects.
There are subtle differences however. Composition - Filled diamond
Aggregation - Unfilled diamond \### Aggregation The notation of
aggregation would be used when an instance of class A *holds a
collection* of instances of class B. An example being a list. The
objects can exist independently. Used to model **has a relationships**

``` java
public class Organization
    private List<Person>employees;

    public class Person {
        private String name;
    }
```

### Composition

Represents a part whole relationship. Such that class B is an integral
part of class A. This relationship is made to show if class A *cannot*
exist without the existence of class B. We can consider this one class
having **ownership** of another. Used to model **a part of
relationships**. The existence is tied to another object.

``` java
public class Car {
    private final Engine engine;

    public Car() {
        engine = new Engine();
    }
}
```

1.  Benefits

    - We can control the visibility of other object to client classes
      and only reuse what we need.
    - A Client object will be able to communicate with an engine but not
      a piston
    - If we need to change anything in the piston class, the car class
      may need to change but not a client class \#### Comparing to
      Inheritance It is easier to change the class which is implementing
      composition over inheritance In inheritance, you cannot add a
      method to a child class with the same name but a different return
      time as the parent class. Composition however lets us change the
      interface of a front-end class without affecting back-end classes.
      Composition is done at run time via *dynamic binding*, while
      inheritance is done at compile time, *static binding*.

### Association Classes

They allow you to add attributes, operations, etc to associations Say we
have a person class and a company class. Person -\> Works for -\>
Company Instead of storing the salary and job title in the person class,
since that\'s not really what a person is, we can store this in the
works for relationship \#### Benefits - Adds an extra constraint, there
can only be one instance of the association class between any two
participating objects \### Self Associations Also known as *reflexive
associations*. If the same class appears twice in an association, the
two instances do not have to be the same object. An example is a someone
may have a *friend association* with another person. \### Qualified
Association It is the UML equivalent of dictionaries/hash
maps/associative arrays. A *qualifier* may be used in an association. It
distinguishes the set of objects at the far end of the association based
on the qualifier value. An association with a qualifier is a qualified
association. \## Look more into \^
