## In the stage of OO analysis we:
1. Elicit Requirements
2. Specify Requirements
3. Create conceptual Models

# Analysis Patterns:

- **Patterns** embody the knowledge and experience of many developers 
- A common belief is that the analysis patterns can imporve cocneptual modelling quality because they are proven solutions and have been scrutinized by many devs


## Roles

- **Roles** are what any concept or class would play within the context of its related concepts

- For example a company would be the supplier of some specific product ie a supplier is a role

- Role analysis patterns are pre-cooked solutions for typical role analysis problems
- Each pattern uses simple UML static modelling elements: classes, inheritance, associations and association classes.

### 5 role patterns
1. Role inheritance
2. Association roles
3. Role classes
4. Generalised role classes
5. Association role classes


# Role inheritance

- Roles can be types
- A company that supplies products is a vendor
- A company buying product is a customer

## Metamodel

- A metamodel disallows multiple roles to be played by the same class meaning that it is either a vendor or a customer but not both.
- Similarly a person can be either a dentist or a landlord but not both
- Of course there edge cases for this concept but this will be too restrictive when attempting to meet your own problem domain requirements
- Because they supply a general answer they are known as patterns 
# The association role solution

- With the association solution a company can either sell or buy any product and therefore can be considered as a customer or a vendor at any time as well as the same time
- All it takes is a link to allow a company object to play the role of a customer and or vendor for a specific product instance.


## The association role pattern
- This is taken care of by association and its own role. The association role metamodel describes our second rule pattern.

- This is clearly a very economical way of dealing with roles and you'd be amazed to see how many problems can be solved with just one feature

### Problem with this

- The fact they don't allow any attributes

# Role class


- This allows you to link any company with any of the roles it may possibly play. Here a role is modelled with a class - as it was in our inheritance solution.

 - In delegation an object handles a request by delgating toa second object, the delegate is a helper object.
# Role class Generalisation

- All roles may be modelled and dynamically asigned to any company at a time. Role specific operations are supported but not erpreated thanks to the UML generalization features
- These role specific operations simply reside in one place:The role generalisation class. It is the one class that will take care of all delgations to company.


- Only one implementation of its operations is needed and it will serve all class Roles by virtue of inheritance.

# Role association class
