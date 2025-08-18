# OO Analysis 
- Domain or Business Analysis and modelling
- IT System modelling and design
- IT systems interfacing

# Domain

- Subject area to which user applies a program is the domain of the software
- The critical complexity of most software projects is in understanding the domain itself
- A problem domain is the area of expertise or application that needs to be examined to solve a problem
- A problem domain is simply looking at just the topics you are interested in and excluding everything else
- For example if you were making a system trying to measure good practice in medicine you cant include carpet drawings at hospitals in your problem domain. in this example, domain refers to relevant topics solely within your interest.

- A domain model illustrates the conceptual classes in the problem domain
- The concepts discovered tend to lead to design classes
- It is a representation of real world conceptual classes, not of software components
- It is not a set of diagrams describing software classes/objects with responsibilities.

## Domain Model
- DM is a visual representation of conceptual classes or real world objects
- AKA conceptual models domain object models and analysis object models
- We illustrate our DM using class diagrams of the UML
- This shows:
	- Domain conceptual classes
	- Associations between conceptual classes
	- Attributes of conceptual classes
- It is a visual dictionary of the note worthy abstractions domain vocab and info content of the domain
- It ignores software artifacts like a window or a database
- Considering object responsibilities during design is important but not in analysis.
- A central distinction between OO analysis and structured analysis is division by classes rather than by functions
- So for a real world domain of sales in a store we may have a sale store and register
### Conceptual Classes
- The DM illustrates conceptual classes of vocab in the domain
- A conceptual class is an idea thing or object.
- It has a:
	- Symbol: Words/Images to represent conceptual class
	- Intension:  definition of the conceptual class
	- Extension: set of examples to which the conceptual class applies to 
### Advantages
- Once modelled the model becomes a stable basis for developments of applications in this domain
- Describes and contains the scope of the problem domain.
- Can be effectively used to verify and validate the understand of the problem domain among various stakeholders.
- It is mainly helpful as a communication tool and a focus point both among the different members of the business team as well as between the technical and business teams

### When to create a DM

- It is not a big bang approach where one tries to capture all conceptual classes
- We only build a DM for the scenario/user story under investigation
- Try to find as many concepts as you can if you don't find them all they may be discovered in later iterations

# Three strategies to find classes
- Reuse or modify existing models
- Use category list
- Identify noun phrases

## Attributes vs classes

- Not easy to decide 
- If not a number it exists in a real world it is probably a class
- In real world store is not a number or text, it actually exists

- Airport is a thing whereas destination may be an attribute of a flight
- In an airport class flight cant be an attribute as it is an actual event/occurrence

## Description Classes