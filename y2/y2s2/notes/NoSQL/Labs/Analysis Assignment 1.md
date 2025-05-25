## The basic rules for designing a relational database:
1. Never create an entity type for the system or the client you try to model. For example, you are required to create a database for a Library system. You should never create an entity type called Library.
2. An entity type must have at least one non-key attribute/property. The non-key attribute is any attribute that is not a PK.
3. The name of each entity type must be singular, not plural.
4.  For the conceptual diagram, it only contains entity types and relationships. No attributes and associative entity types.
5. For the ERD diagram, it contains keys, attributes, associative entity types. No many-to-many, ternary relationships. It only contains unary, binary, one-to-many relationships.
6. For the binary one-to-many relationship, the entity type associates with one side is always the parent and the one associated with the many side is the child. 
7. If you have a many-to-many ternary relationship in the conceptual diagram, we only need to introduce one associative entity type in ERD.


For the textual description of requirements of the system to be designed:
Trick: filtering out keywords sentence by sentence
noun: entity type or attribute
verb: relationship

Potential Entities:

- The contains **flight** statistics for all **airports** in the United States from January 2011 to December 2020. Each **observation** is reported by month, year, airport, and **airline**. Flights can be categorised as on time, **delayed**, **cancelled**, or **diverted**. Flight delays are attributed to five causes: carrier, weather, NAS, security, and late aircraft

Each column is an attribute. We need to group columns into a logical entity type.

- Flights: 
	- Airport - Foreign...
	- 

The first step is to examine the data set closely and get to know the data.
We need to check duplicates and empty cells for each column.

Tricks to check blanks can be found https://www.exceldemy.com/find-blank-cells-in-excel/
Usually, I use countblank function. The blanks indicates that the attribute can take null values.

Tricks to check duplicates can be found https://support.microsoft.com/en-us/office/find-and-remove-duplicates-00e35bea-b46a-4d5d-b28e-66a552dc138d
Use dupliates to identify the possible relationships between entity types.

cabin contains blanks

duplicates: age, cabin, embarked, fare, parch, pclass, sex, sibsp, survived, ticket, title,
family_size

The second step is to examine each variable in the data set dictionary. We apply the same 
trick to filter out some keywords. If we cannot find another attribute in the data set 
dictionary to describe the noun, you cannot consider it as an entity type.

Step 2:
survived - Passenger
pclass - Two possibilities (one it is about the class of Ticket, the other is about the 
class of cabin)
sex - Passenger
age - Passenger
sibsep - Family
parch - Family
ticket - Ticket (Cannot be PK as it contains duplicates in the data set, but we can use it 
to induce possible relationships)
fare - Ticket
cabin - If pclass is the class of Ticket, it should be attribute of Ticket; otherwise it is 
an attribute of the Cabin class
embarked - Ticket
name - Passenger
passengerID - passenger (PK)
title - Passenger
family_size - Family

Two possible designs:
Assumption: pClass is considered as the class of Ticket
Three Entity Types: Passenger, Ticket, Family
Attributes for Passenger: passengerID (PK), survived, sex, age, name, title
Attributes for Family: sibsp, parch, family_size
Attributes for Ticket: ticket, pclass, fare, cabin, embarked

Assumption: pClass is considered as the class of cabin
Four Entity Types: Passenger, Ticket, Family, Cabin
Attributes for Passenger: passengerID (PK), survived, sex, age, name, title
Attributes for Family: sibsp, parch, family_size
Attributes for Ticket: ticket, fare, embarked
Attributes for Cabin: pclass, cabin

Step 2: Explore the relationships and multiplicity, mainly focus on PKs if there is one. If 
there is no PK, we need to examine each attribute. Apart from PKs, we need to also pay 
attention on one/two duplicates. If no duplicates and blanks, the relationship must be one-
to-one.If no duplicates but there are blanks, the relationship could be either 0...1 to 1 
or 1 to 0...1

Entity Type: Passenger, Ticket, Family

passengerID (PK) for Passenger
ticket is not PK for Ticket

passengerID -> parch 1...1
parch -> passengerID 1...*

passengerID -> sibsp 1...1
sibsp -> passengerID 1...*

passengerID -> family_size 1...1
family_size -> passengerID 1...*

The relationship between Passenger and Family
is 1...*

passengerID -> ticket  1...1
ticket -> passengerID  1...*

The relationship between Passenger and Ticket
is 1...*

ticket -> parch 1...*
parch -> ticket 1...*

ticket -> sibsp 1...*
sibsp -> ticket 1...*

The relationship between Ticket and Family is 
*...*

For 1...* relationship, entity type associated
with 1 is parent, entity type associated 
with * is child. The FK will always generated 
in child side.

Every entity type in ERD must have one PK.
If the entity type does not have a natural PK,
we invent a surrogate key.

Step 3: Draw conceptual diagram and ERD diagram
Be careful the data type of each attribute, it must be consistent what you have in the 
data set. For example, the ticket should be varchar. If you have it as integer, it is incorrect as it is inconsistent with the one in the data set.
