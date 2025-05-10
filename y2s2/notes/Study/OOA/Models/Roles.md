# Modelling roles

## OO analysis 

- In OOA for requirements we
1. Elicit: Define what does the software need to do and what's the problem the software is trying to solve.
2. Specify: Describe the requirements, usually with use cases/user stories 
3. Identify (Creating a conceptual model): Identify the important concepts attributes and relationships. 

## Roles

- **Roles**: The function/purpose that any class would serves within the context of its related classes.
### 5 role patterns:

1. Role inheritance
2. Association Roles
3. Role classes
4. Generalised role classes
5. Association role classes
- Each role pattern has its own power, flexibility and complexity.

## 1. Role inheritance
- One can think of roles as types and in programming, objects or classes, a company supplying products is a vendor however when someone buys, their role is a customer, they are both which can  be illustrated through inheritance

### Metamodel

- We can also use a metamodel as a means of abstracting this inheritance, the inheritance
  meta model does not allow multiple roles to be played by the same base class.
- For examples, a company a company can either be a vendor or a customer but not both 
- Similarly, a person can be either a dentist or a landlord but not both
- There are also cases where this limitation will be too restrictive to meet domain requirements
- Metamodels supply a general answer, they are also known as patterns

### Inheritance solution restrictions 
- The inheritance metamodel disallows multiple roles to be played by the same base class.

## 2. Association roles

- The association role solution is essentially providing the role through the description of associations
- For example, have a Person class, and they are described as a buyer in the association between them and the product

- Company can either buy or sell any product and then be considered a customer
  or a vendor as well as at the same time. 

- This association role is moreso to do with the links between the classes, 
  here something can be linked to a class in two different ways

- It allows for as many base class instances and client instances as need be

- All it takes is a link to allow a company object to play the role of customer 
 or buyer for a specific product.

### Drawback:

- Don't allow attributes on the idea of being a particular role (buyer/seller)
- If we wanted to record how much money this buyer or seller has given or received,
  then we'd have to come up with a role class solution since classes can hold 
  attributes

## 3. Role classes

- The role class solution allows once to link any object with another through an intermediary class. 

- This allows for flexibility or multiple roles while still providing the power of role specific attributes and class operations

- This role pattern is simply a middle man between the client and base class, and a client will look at role as if it was the base class itself

## 4. Generalised role classes 
## 5. Association role classes
## More terms


- **Patterns** : embody the knowledge and experience of many devs
- **Roles**: What any class would play within the context of its related classes.


# Conclusion:

