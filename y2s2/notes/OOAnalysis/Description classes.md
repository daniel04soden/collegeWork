
- Conceptual class or attribute 
- It is not always easy to decide IE the account number
- If it is not a number of text in real world it is probably a conceptual class

- A description class contains information pertaining to something else

-  Description of different items form the item itself There may be no items but still
    a description

- It is better to keep description of items separate
- Else if the last item is removed then you lsoe the description
- Also reduces duplication of storing item info many times
- Represents a description of info about items
## Example


- Item with an attribute description price serial number and id
- We would be better off splitting these up in a sense where we have item id in item and serial price description and anything else in a class like product specification


- Many examples of specifications or descriptions include books mobile phone packages cars etc

## Domain model

- this concept doesn't exist in the UML but for us it is a real thing whereas a class is a software thing
- The UML definition of  a class is a description of a set of object that share the same attributes, operations methods relationships and semantics some people like to call it an implementation class
- In the UML an operation is a service that can be request from an object to effect behaviour
- A method is the implementation of an operation

# Terminonlogy

- Concpetual Class; Real world concept or thing which belongs to the DM
- Software class is a class represeting a specification or implementation of a software component regardless of the process or method
- Design class: A member of the design model ie a synonym for a software class

- By using the names of the real world in our model we can map names to design the model and ultimately structure our code
- The domain model provides a visual dictionary of the domain vocab from which to draw inspiration form the naming of things in the software design
- This relates to the issue of a representational gap or semantic gap - the gap between our mental model of the domain and its representation in software

## Visualising a domain model

- A class describes a set of objects with the same semantics, properties and behaviour when used for domain modelling it visualises a real world concept
- During a semester for example a lecturer may read one or more lecture and a student attends one or more lectures
- During the semester there will be several  exercises
- Each student is then assigned to one study group

# Associations/Relationships

- Relationships between classes
- The ends of an associations are roles
- Roles also have a multiplicity name and navigability
- A lecturer reads one or more lectures
- A student attends one or more lectuers
- A study group conssits of two to three sutdents
- During the semester there will be several exercises

- Two classes can also have multiple associations
- A student usually attends any number of lectueres
- A study group consists of two to three students
- After submitting a solution it is graded by a tutor who is also a student







