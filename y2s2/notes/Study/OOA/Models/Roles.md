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

## 1. Role inheritance pattern
- One can think of roles as types and in programming, objects or classes, a company supplying products is a vendor however when someone buys, their role is a customer, they are both which can  be illustrated through inheritance

- In plain English this model tells us that there exists two different types of companies: customers and vendors. Customers buy products whereas vendors supply them. Because inheritance is being used, this model also says that a company cannot be both a vendor and a customer; it’s either one or the other—and once the choice is made, it’s final.
### Metamodel

- We can also use a metamodel as a means of abstracting this inheritance, the inheritance
  meta model does not allow multiple roles to be played by the same base class.
- For examples, a company a company can either be a vendor or a customer but not both 
- Similarly, a person can be either a dentist or a landlord but not both
- There are also cases where this limitation will be too restrictive to meet domain requirements
- Metamodels supply a general answer, they are also known as patterns

### Inheritance solution restrictions 
- The inheritance meta-model disallows multiple roles to be played by the same base class.

## 2. Association role pattern

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

## 3. Role class pattern

- The role class solution allows one to link any object with another through an intermediary class. 

- This allows for flexibility or multiple roles while still providing the power of role specific attributes and class operations

- This role pattern is simply a middle man between the client and base class, and a client will look at role as if it was the base class itself

- The role class pattern uses Delegation in that it hands off the overhead of a request made to it to another object.

## 4. Generalised role class pattern

- The Generalised role pattern all roles may be modelled dynamically and assigned to any Object  (Company) at any time
- Role specific operations are supported but not repeated as a result of UML generalisation
- Role specific operations are kept in one place, the Company role or   Object role generalisation class. It is the one class that will take care of all delegations to the company.
- IE the operations are only implemented once and it will serve all class roles by means of inheritance.

## 5. Association role class pattern

- Roles are created on demand ie for companies, if we want to specify that a company supplies a particular product, we just nee to create an instance of role, a link object and then link the proper product to the right company. 
- The main idea here is that we have two objects created for roles, the role assignment and role type. For example, in the association between company and product, we assign a role and this role is a type of say buyer or seller.
- In this new model, role type takes care of all cases but each actual role type still requires the creation of a role type object.
- The concept of roles is modelled, not roles themselves, this model is more abstrant than models that deal with explicit roles. 

## More terms

- **Patterns**: Embody the knowledge and experience of many devs
- **Roles**: What any class would play within the context of its related classes.
- **Delegation**: the process by which one object handles a request by handing off the data to a seccond helper object


