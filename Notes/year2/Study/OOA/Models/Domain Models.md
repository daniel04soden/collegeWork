# Domains

- The subject area to which the user applies a program is the **domain**
  of the software

- Hardest part of software is understanding this domain **(critical
  complexity)**

- **Problem domain** - the area of expertise that needs to be examined
  to solve a problem

- If measuring KPI on sales, wouldn\'t measure ability to pick up
  rubbish, task of the day for workers but not what you\'re tasked with
  measuring. Within the domain we are working with, but not something to
  consider for this software project. \# Domain Model

- **Domain Models** illustrate the conceptual classes in the problem
  domain

- Concepts discovered tend to lead to design classes

- **Not** real world conceptual classes, **not** of software components
  and **not** set of diagrams describing software classes or software
  objects with responsibilities

- We **decompose** the **domain of interest** into individual classes or
  objects of the real world

- It is a **visual representations of the conceptual classes** or real
  world objects

- We illustrate a Domain model **using class diagrams** through domain
  conceptual classes, associations between classes and attributes of
  conceptual classes.\

- The domain model is a **visual dictionary of the noteworthy
  abstractions** domain vocab and info around the general domain

- It **ignores software artifacts** such as a window or database \##
  Advantages of domain models

- Model becomes a stable basis for subsequent development of
  applications in the problem domain

- Can be used to verify and validate our understanding of the problem
  domain among various stakeholders.

- It is especially helpful as a communication tool and a focusing point
  both amongst the different members of the business and technical team

- A central distinction between OO analysis and strucuted analysis is
  divisio by classes rather than by functios

- So for a real world of domain of sales in a store we may have sales,
  stores and registers. Whereas in the strucutred analysis, we have a
  customer registering for the store, a sale being made and a process
  being completed by the register

## When to create a domain model

- Domain models are grown incrementally, it is not a big bang approach
  where one tries to capture all conceptual calsses before work is
  started,

- We build one only for the scenario/user story under investigation.

- You do try to find as many concepts as you can and if you don\'t find
  them now, they can be found later on new iterations. \### Three strats
  to find classes

1.  Reuse or modify existing models
2.  Use a category list
3.  Identify noun phrases

- For example:
  - Physical: registers planes
  - Transactions: sales, payment ,reservation objects
  - Roles: Cashier, pilot
- Identify noun and noun phrases in text of the scenario of a use
  case/user story
- From these and the concept category list as seen in the examples
  above, we identify candidate concepts some may be attributes of other
  concepts

### Deciding on: Classes or attributes

- Conceptual class or attributes
- If it is not a number or text in real world, it is probably a class
- In the real world, a store isnt a number or descriptio of something,
  it is tangible
- If in doubt make it a separate concept. \## Description classes
- Contains info that describes something else
- IE a class used to attain normalisation - Item, itemDescriptionm
- The descriptio of the item may be differentt from the item itself, so
  it is better to keep it separate.
- Else if the last item removed, then you have nowhere to retain that
  description
- Reduces duplication - every time someone purchases item, do they need
  that description.
- A Description represents informatio about items
- Use them when there needs to be more info about an item or service,
  outside of its current existence/instances of it.

## More on domain model itself

- DMs do not exist explicitly in UML, but for us it is a real world
  thing whereas a class is a software thing.
- Some people like to call it an implementation class

## Terminology

- In UML, **operations** are a **service that can be requested from an
  object** to effect behaviours.
- A **method** is the implementation of an operations
- A **class** is a description of a set of objects that share the same
  attributes, operations, methods, relationships and semantics.
- **Conceptual class**: real world concept or thing, belongs to the
  domain model, technically not a part of UML
- **Software class**: class representing an implementation of a software
  components regardless of the process or method
- **Design class**: a member of the design model, synonym for a software
  class
- **Implementation** class, a class implemented in an OOL (Java,c++)
- **Class** represent a real world thing or a software thing
- **Domain Model** provides a visual dictioary of the domain vocab and
  concepts from which to draw inspiration for the naming of things in
  the software design
- **Semantic gap**: Gap between mental model of domain and its
  representation in software.
- **Attributes**: Logical data values of an object
- **Association**: Relationship between classes.
- **Role**: The ends of an association eg A lecturer reads one or more
  lectures, the student attends one or more lectures, study group
  consists of two or three students. During the semester there will be
  several exercises
- **Constraints**: constraints express rules of the problem domain in
  the specification model, restricting the possible instances of the
  model.
