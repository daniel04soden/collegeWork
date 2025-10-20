# Rough Work for Nosql lab 1

- survival -\> Passenger entity type

- pclass -\> Ticket entity type

- sex -\> Passenger entity type - Non key attribute - Passenger

- Sibsp -\> Family entity type - Entity type

- Ticket -\> Primary key of Ticket (Candidate key maybe)

- Cabin -\> Ticket entity type-\> Ticket no

- Fare -\> Ticket entity type

- embarked -\> Port/Ticket entity type

- Name -\> Attribute of passenger entity type

- PasssengerID -\> Unique id of passenger - Primary key of passenger

- Title -\> Entity type passenger - Non in conceptual diagram

- Family~size~ -\> Family entity type

- Design 1: Entity types: Ticket, Passengers Family

- Design 2: Ticket,Passenger, Family ,Cabin, Port

- Relationship - Passenger, ticket:

  - 1...\* Passenger purchase 1 ticket

- Relationship - Family, passenger

  - 1..\* passenger: Passenger belongs to 1 family
  - Each family must have 1 passenger

- Relationship Cabin ticket

  - 1 ticket assigned to 0..1 Cabin

- Relationship between Port and Ticket:

  - 1 ticket assigned to 1 port

- Best overall design: Design 1

- Ternary Relationship from passenger ticket and family.

- 1 family must buy 1 ticket for his family members as passengers.
