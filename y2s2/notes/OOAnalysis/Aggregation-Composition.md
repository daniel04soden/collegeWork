- Aggregation represented by an unfilled diamond
  - Holds a collectoin of instances of classs B. The objects can exist idependents and where the class A object knows about which class B objects can vary over time
- Aggregations are used to model a has a relationship
- Composition: Filled in Diamond

- A composition represents a whole relationship such that class B is an integral part of class A. This relationship is typically used if objects of Class A cant logically exist without having a class B object

## Example

- An engine is a part of a car
- An organisation has employees

# Composition

- When once class owns another class and the other class cannot meaningyfully exist when it's owner is destroyed
- Since an engine is a pary of a car th relationship between them is compositon and here they are implemented between Java classes

## Benefits

- Can control the visibility of other object to client classes and reuse only what we actually need
- A client object will be able to communicate.

# Aggregation

- One to many or many to many
- An organisations has people as employees and the relationship between them is employees

- Aggregation can be a has a relationship

# Link attributes

- How to represent salary and job title
- Why not this
- Salary and job title are not properties of a person

- A person can have more than one association with a company
- A person can have a competency of a particular skill

# Association class subtitles explained

- In the uml only the latter case is legal. IE you can have only one competency for each combination of person and skill
- many people do not think about it at all and assume the constraint in some places and not in others. When using uml remember that the constraint is always there

## Reflexive associations

- If the same class appears twice in an association
- The two instances do not have to be the same object and they usually aren't

## Qualified association

- Has a qualifier used to select an object from a larger set of related objects based on the qualifier key
- Qualified asssociation is the uml equivalent known as associative arrays
