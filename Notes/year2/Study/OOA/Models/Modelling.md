# What are models?

- Simplifications of reality
  - Structural - Simple class diagram - compile time - blueprint
  - Behavioural - Object diagram ie runtime - includes the operations
    \## Aims of models
- Visualise systems
- Specify structure and behaviour of a system
- Template to construct a system
- Document decisions we made - assumptions \## Why models
- Abstracts reality and filters out non-essential details
- This helps us deal with complexity and deal with human limitations \##
  Why objects?
- Reflect reality more accurately
- Reduce semantic gap between reality and a model \# Principles of
  modelling

1.  The **choice** of what models to create has a profound impact on how
    a problem is attacked and thus a solution is formed
2.  Every model may be expressed at different levels of **precision**
    (generally in a blueprint form, more exact showing everything from
    the fields to the constructor and method parameters)
3.  Best models are connected to **reality**. Structured analyssi
    techniques fails to birdge the gap between the analysis model and
    system design model andas a result, the system conceived will
    diverge over time.
4.  **No single model is sufficient.** To model a system accurately, why
    can\'t we just make a domain model, doesn\'t it cover the entire
    system?.

## 1. Choice

- The models you choose to make profoundly influence how the problem is
  attacked and how a solution is shaped.

  - In software, the models you choose greatly affect your world view.
  - This view leads to a different kind of system \## 2. Precision

- Every model may be expected to be expressed at a different level of
  precision.

- The best kinds of models, let you choose the degree of detail

  - Who is viewing the model?
  - Why they need to view it. \## Reality

- All models simplify reality

- A good model reflects potentially fatal characteristics \## No single
  model

- Every non-trivial system is best approached through a small set of
  nearly independent models.

- Create models that can be built, studied separately but still are
  interrelated. \# UML

- UML or unified modelling language is a modelling structure used for:

  1.  Visualise
  2.  Specify
  3.  Construct
  4.  Document

- The artifacts of a software intensive system

- It provides common vocab of OO terms and diagramming techniques rich
  enough to model any systems development project fro analysis through
  implementation \## UML Structure diagrams

- We can represent the data and static relationships in an info system:

  - Classes
  - Objects
  - Packages
  - Deployments
  - Components
  - Composite structures

## UML Behaviour Diagrams

- Depict the dynamic relationships among the instances or objects that
  represent the business information system. \## Class Diagrams
- Class diagrams describe the structure of an OO System by showing the
  classes in that system and the relationships between the classes
  \![*Study/OOA/Models/Screenshot 2025-05-06 at 14.09.10.png*]{.spurious-link
  target="Study/OOA/Models/Screenshot 2025-05-06 at 14.09.10.png"} \##
  Sequence diagram
- Describes the interaction among a set of objects participated in a
  collab and arranged in chronological order \![\[Screenshot 2025-05-06
  at 14.11.19.png\]\] \## Component diagram:
- Classifying groups of classes into components. These kinds of diagrams
  support the interchangeability and reuse of code \![\[Screenshot
  2025-05-06 at 14.12.47.png\]\] \## Package diagram
- Used to simplify complex class diagrams. This allows us to group
  classes into packages.
  \![*Screenshot 2025-05-06 at 14.13.46.png*]{.spurious-link
  target="Screenshot 2025-05-06 at 14.13.46.png"} \## State machine
  diagram
- Typically used to describe state-dependent behaviour for an object
- For example having an order progressing through state -
  placed,processed, dispatched, en-route, delivered \![\[Screenshot
  2025-05-06 at 14.14.08.png\]\]
