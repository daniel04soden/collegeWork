They are a type of UML diagram. They visually represent the interactions between objects or components in a system over time. They focus on the order and timing of messages.
### Why?
They are useful for represent and analysing use cases. Makes it clear how specific processes are executed. 
They provide an intuitive way to convey behaviour, helping teams understand complex interactions.
They are good for visually dynamic behaviour. Helps us understand workflows better.
### Sequence Diagram Notations
###### Actors
In a UML diagram, an actor represents a type of role where it interacts with the system and its objects. An actor is always out of scope of the system we aim to model. 
###### Lifelines
it is a named element that depicts an individual participant in a sequence diagram. Each instance in a sequence diagram is represented by a lifeline. 
###### Messages
Communication between objects is depicted using messages. The messages appear in a sequential order on the lifeline. Represented using arrows. 
- Synchronous messages wait from a reply before the interaction can move
- Asynchronous messages do not wait for a reply before they can move
To visualise a message being **deleted**, we use an **X** symbol through the end of the message.
	There are also **self messages**, which are a self loop, represented with a u-turn arrow.
**Reply messages** are represented with a dotted line. Used to show messages being sent from the receiver to the sender. 
**Found messages** are messages sent where we do not know the source of the message. These are represented with a dot and an arrow out of it to the lifeline.
<---O
**Lost message** represent messages to where the receiver is not known by the system. Represented by O--->
###### Guards
Guard conditions are used when we need to restrict the flow of messages on the pretext of a condition being met. Guards play an important role in letting developers know the constraints attached to a system`.
### Interaction Operators
###### Alt 
Alternative operator. Represents an if-then-else statement in a sequence diagram. Only one alternative runs on any given run through a program. It only runs with the guard condition tests true. If there is no guard it will always run.
###### Opt
Optional operator. Represents the logic of an if-statement. To run, the guard condition must be satisfied. If the guard condition is failed, then it is ignored.
###### Loop
Loop operator. The indicated section will run repeatedly. The number of times it runs is determined by the `minint` and `maxint` parameters of the operator.