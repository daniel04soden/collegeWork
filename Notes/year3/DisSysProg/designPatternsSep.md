# What are design Patterns:

## Gang of four patterns:

- A solution to a problem typically shared within the same address space

## Architectural patterns:

- A solution to a problem often across multiple address spaces, multiple
  processes or services

# Design pattern Template:

``` org
* Design Pattern:
** Intent/Motivation
** Application/Known uses
** Structure/Participants/Collaborations
*** Consequences
** Implementation/Sample Code
*** Similar patterns
```

# Model View Controller:

## Intent/Motivation:

- Before MVC, user interface designs tended to lump all of the objects
  together. This would lead to a mess int terms of priority of concerns.
  MVC decouples them to increase flexibility and reuse

## Application/Known uses

- Building user interfaces

## Structure/Participants/Collaborations

- Consists of three kinds of objects, Model is the application object,
  the view is its screen presentation and the controller defines how it
  reacts to user input.
- A view must ensure that its appearance reflects the state of the
  model. Whenever that models state changes, the view must update
  depending on waht changes occured. In response each view is given
  opportunities to update itself (event loop, onclick etc)

# Factory Method:

## Intent/Motivation:

- Defining an interface for creating an object but let the subclasses
  decide what class to instantiate. It lets a class defer instantiation
  to subclasses.
- Frameworks use abstact classes to define and maintain relationships
  between objects. A framework is often responsible for creating these
  objects as well.
- AKA Virtual constructor

## Application/Known uses

## Structure/Participants/Collaborations

### Consequences

## Implementation/Sample Code

### Similar patterns
