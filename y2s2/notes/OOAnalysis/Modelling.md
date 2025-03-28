
# What is modelling?

## Modelling can be simplified down to:
- A Simplification of reality
- Every system may be described from different aspects using different models and each model is thus a semantically closed abstraction of the system.
- A model may be structural emphasising the organisation of the system or it may be behavioural, emphasising the dynamics of the system.
# Aims of Modelling

## Through modelling we achieve four aims

1. Models help us visualise a system as it is or as we want it to be 
2. It permits us to specify the structure or behaviour of a system
3. Models give us a template that guides in constructing a system
4. Models document decisions we have made

- **We tend to build models of complex systems as we can't comprehend such a system in it's entirety**

# Methods of modelling

## Algorithmic Modelling

- Main building blocks are procedures or functions. Leads devs to focus on issues of control and decomposing larger algos into smaller ones. They become brittles as little changes become hard to maintain. If ever a major change would be needed the system could fall apart.
## Object oriented modelling

- Main building blocks are classes and objects.
- Generally drawn from human vocabulary in the context of achieving a solution or a  problem space.
- A class is a description of a set of common objects.
- Every object has identity state and behaviour. 
- Identity state can be attributed to fields and behaviour may be attributed to functions/methods.

### Why would we use models?

1. Abstract reality
2. Filter out details
3. Help deal with complexity
4. Human limitations
5. Allow us to focus on the big picture
6. To promote understanding cleaner design and maintainable systems
### Why objects?

- To reflect reality a lot more accurately
- Reduce semantic gap 

#### Conceptual

- Do not consider any aspects of implementation of objects
	- Lets us focus on identifying the objects in the application domain

#### Specification

- Consider interfaces of objects 
	- Focus on how objects interact in the solution domain
-
#### Implementation

- Considers all details of objects
	- How they are implemented in code

# Teams often don't model

- Many software teams builds apps like they are building paper planes
- In that they:
	- Code from project requirements
	- Work long hours
	- Lack planned architecture
	- Doomed for failure


# principles of modelling

1. Choice of what models to create
2. Every model may be expressed at different levels of precision.

- An analyst or end user will want to focus on what rather than developers who focus on how.
- Both stakeholders will want to visualise a system at different levels of detail at different times.

3. The best models are connected to reality. structured analysis techniques consistently fail to bridge the gap between the design and system model. As a result the system conceived and the system built diverge over time.
4. No single model is sufficient. Sub models make up the entire system.

## 1. Choice of model

- The models you create influence how a problem is attacked and how the solution is shaped.
- In software the models you choose greatly affect your world view and each world view leads to different types of systems
- Process model -> Deployment model -> Design model


## 2. Every model may be expressed at different levels of precision.


- Model may be expressed at different levels of precsions
- The best kind allow you to choose the degree of detail depending on both who and why they are viewing the model

## 3. The best models are connected to reality.

- All models simplify reality
- A good model reflects potentially fatal characteristics.

## 4. No single model is sufficient. 

- Sub models make up the entire system.

# UML - Unified Modelling Language

## What even is UML ?

- The UML language is used for:
1. visualising 
2. specifying  
3. constructing 
4. documenting

- IE the artefacts of a software intensive system.

## Unified Modelling Language

- It provides a common vocab of OO terms and diagramming techniques rich enough to model any systems development project form analysis through implementation
- Version 2.5 has 15 diagrams in 2 major groups those being
	- structure diagrams 
	- behaviour diagrams

- It reperesents the data and the static relationships in an information system
1. Class
2. Object
3. Package
4. Deploytment
5. COmponent
6. composite Structure

### UML behaviour diagrams
- Depicts the dynamic relationships among the instances of objects that represent the business information system
- It is a language for visualising in that communicating conceptual models to others is prone to error unless all speak the same language
- there are things about a soft system you can't understand unless you build models/
- An explicit model facilitates communication.
- It is a language for specifying in that the uml builds models are precise unambiguous etc.

- It is intended for a variety of languages but in particular java,c++ and other object oriented languages. Something like Ocaml would be useless for UML.
- Permits forward and reverse engineering

- All diagrams have symbols which contain text and have linked lines with relationships
- these relationships are represented by different lines to represent different relationship meaning
- these may have text and numbers on them

### Use case diagrams

- Use case diagrams capture system functionality

### Class diagrams

- Describes the structure of an OO system by showing the classes in that system ,their fields,functionality and how they interact with one another
### Sequence diagram

- This describes an interaction among a set of objects participated in a collaboration arranged in chronological order.
### Component diagram

- Classifying a group of classes into components and support the interchangeability and reuse of code.

### Class diagram

- Package diagram used to simplify complex class diagrams and you can group such classes into packages
### State machine diagrams

- Used to describe state dependent behaviour for an object
