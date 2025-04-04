# Boundary and control classes

- Most use cases imply at least one boundary object that manages the diagloufe between the actor and the system in the next sequence diagram it is represented by the lifeline add advert UI

- The control object is represented by the lifeline :"AddAdvert" and this manages the overall object communication.

## Reply message

- Returns the control to the object that originated the message that began the activation
- Reply messages are shown with a dashed arrow, but is optional to show them at all since it can be assumed that control is returned to the original class

- The alt interaction operator shows branching
- Two interaction operands, one for each alternative
- Ref interaction operator indicates interaction occurence that references an interaction fragment

# Guidelines

1. Decide at what level are you modelling the interaction
2. Identify main elements involved
3. Consider Alternate Scenarios

## Model Consistency

- Allocation of operations to objects must be consistent with the class diagram and the message signature must match that of the operation
- Can be enforced through case tools
- Every sending object must have the object reference for the destination object
- Either an association exists between the classes or another object passes the reference to the sender.
- The issue is key in determining association design
- Message pathways should be carefully analysed