A general view of program structure shared by UML and object-oriented programming languages.
Computation in objects take place that
- *store data* and implement behaviour
- *are linked* together in a network
- *communicate* by sending messages
- are described by *classes*
Because OOP and UML share the same object model design, UML diagrams can easily be implemented, and it is easy to document OOP programs with UML
### Class Notation
###### Class Icons
- Class name
- Optionally show attributes
- Optionally show operations
###### Class Features
- Access levels: public (+) private (-)
### Objects
Objects are created as run-time instances of classes 
###### Object icons
- Shows the object name and class name
	-  Variable names can be used as object names
	-  Object names are optional
	- Class name is preceded by a colon. e.g. myScrew : Part
	- the names are *always* underlined
- The second part shows actual data stored in the object as attribute/value pairs. e.g. name = "screw"
	- this part is usually omitted
Objects have:
- *State*: The value held in an object
- *Behaviour*: The functionality it contains
- *Identity*: a distinguishing property of an object
	- Not an attribute value
	- Could be the object's address in memory
Objects *normally encapsulate* their data
With objects we should *avoid data replication*
All parts have the same kind of attributes like name, number, cost
- It is a waste of resources
- It is hard to maintain
We should make a new class are shared data, call this a catalogue entry. It describes a type of part.
```java
public class Part {
	private CatalogueEntry entry;
	public Part(CatalogueEntry e) { entry = e; }
}
// When parts are created
CatalogueEntry screw = new CatalogueEntry("screw", 234234, 0.02);
Part s1 = new Part(screw);
```
#### Links
References can be shown in UML as links
- Object name is the name of the variable
- field name 'entry' is used to label end of the link
#### Associations
Have:
- a name (not show)
- a role name at each end, derived from the field name
- a multiplicity at either end, to show how many links their can be
- an arrow head at either end, to showing navigability
### Types
Object diagrams show objects and links
- Describes the runtime properties of a program
Class diagrams show classes and associations
- Shows compile-time properties of a program
- The logical structure of its source code
### Messages
Object communication is shown in UML as *message passing* 
- A message corresponds to a method call
Object diagrams with messages show *collaborations*
- Multiple messages can be shown on one diagram
- Return values are shown using the := notation
### Generalisation
Java's extend relationship is represented by generalisation in UML. (Also called specialisation if viewed downwards)
#### Properties
Generalisation is **NOT** an association
- Does now give rise to links between objects
- Usually unlabelled
- Never has multiplicities
Explained by substitutability
- An instance of a subclass can be used whenever an instance of a super class is expected
### Polymorphic Association
Assembles contain Components
- Instances of contains can link assemblies to instances of both component sub classes.
- Component class is abstract
### Why?
Real world models don't represent necessarily good software design