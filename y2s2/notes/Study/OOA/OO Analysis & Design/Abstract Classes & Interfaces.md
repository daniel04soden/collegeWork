### Abstract Classes
They can not be *instantiated*
Like classes they introduce types
- But no objects can have as actual type, the type of an abstract class
Why use them?
- Common set of features and implementation for all derived classes but
	- We prevent users from handling objects that are too generic
	- We cannot give a full implementation for the class
### Interfaces
A set of *methods and constants* that is identified with a name.
The are similar to abstract classes
- You cannot instantiate interfaces
- An interface introduces types
- **They are completely abstract however** ( No implementation)
Classes and Abstract classes *realise* or *implement* interfaces
Interfaces can extend from other interfaces
Why use them?
- To separate (decouple) the specification available to the user from implementation
