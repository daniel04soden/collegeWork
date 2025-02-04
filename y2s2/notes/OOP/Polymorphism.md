# Polymorphism

- It is the third primary concept of OOP and it is considered the most complex one
- Means many shapes
- Have multiple methods with the same name in a single class

## Constructors

- Special method that will be used to build objects
- Does not have a return type
- A constructor must have the same name as that of the class and does not have any return type

- You can overload constructors too, have been seen before...
- Provides default values for missing parameters
- One constructor can call another constructor in the same class but there are special rules

- You can call the other constructor with the keyword this and the call must be the very first thing the constructor does.
## Signatures
- In C every function must have a different name
- In C++ the retrun type is also a part of the signature but not in java
- Whereas in java two methods must differ in names, or the number of types of their parameters

- Signatures in programming is what distinguishes one function or method from another
## Legal assignments

```
double d;
int i;

d = 5;
i = 3.5;
i = (int) 3.5;

```
- Known as widening - adding a floating point position after the number 5
- Narrowing is illegal unless you cast


- As well as this if we have double as an input paramaeter and we put in an int, we are okay to do the as the jvm will simply take in 5.0 instead

- 
# Overloading
- Two or more methods with different signatures
- Overloading is an illusion created to make the user thinks a method can deal with different data types and inputs
- We may see that we have a class which takes in different data types in their methods, this can help in handling different types of data
- Printing things like objects booleans and integers as `System.out.println` is quite heavily overloaded
- JVM will call the print method for the most suitable datatype
- When you overload a method with another very similar methods, only one of them should do most of the work.

## Why overload methods

- Same names for methods
	- Generally do the same thing
- Example:
	- println(lnt)println(double)
# Overriding
- inherits methods from parent classes and rewrites method with the same signature
- 
# In Constructors
