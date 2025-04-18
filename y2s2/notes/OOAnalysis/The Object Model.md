# Objects

- Classes are the containers that create instances of objects
- Because of the shared object model
	- UML diagrams can easily be implemented
	- OO programs can easily be documented in UML
## Reverse engineering
- Creating a UML model for existing code 
- Often useful in dealing with undocumented legacy systems


- Objects created at runtime as instances of classes

- Icons have two compartments
	- Object name and class naem
		- Variable names can be used as object names
		- Object name optional
		- Class name preceded by a value
		- Names always underlined
	- Second shows the actual data stored in the object as attribute/value pairs
		- This compartment is usually omitted
- Objects have state behaviour identity and names.
- State being the data values held
- Behaviour : Functions - the functionality it contains
- Identity: Distinguishing property of an object
	- Not an attribute
	- Could be its memory address
- Names: useful in modelling to refer to an object
- Objects normally encapsulate their data 

# Associations
- Data relationships between classes - Concept of how things are linked
- Links are instances of associations - When these associations are actually put into practice
# Diagrams

- Object diagram show objects and links 
- Class diagrams show classes and associations

## Messages
- Object communication is show in uml as message passing
- A message corresponds to  a method call

## Communication

- Object diagram with messages shows collaborations
	- Multiple messages can be shown on one iagram
	- Return values are shown using the := notation

## Assemblies
- Contain parts
	- Links show which assemble a part belongs to
### Implementing assemblies
- Assemblies could be implemented using a data structure to hold references to objects attributes

## Contains association
- Contains is the association name
- Parts is the role name 
- Assemblies can contain zero or more parts
- Documented by the parts multiplicity.

### Sub-assemblies
- Assembly within an assembly

### Cost of an assembly
-  Add up the cost of all the components (space or time)

# Generalisation

- Java extends relationship is represented by generalisation.
- it is not an association
- It is explained by substitutability.

# Dynamic binding 
- Both part and assembly classes must implement a cost method
- An assembly sends the cost message to all its components

# Applicability of object model
- Some classes inspired by real-world objects
- Real-world doesn't provide a good software design 
- Object Orientation better understood as an effective approach to software architecture