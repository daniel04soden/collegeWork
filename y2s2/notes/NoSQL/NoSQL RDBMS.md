# RDBMS


## Three Tier Architecture

- External Schema:
	- User views
		- IE what the user sees for the database
- Conceptual Schema:
	- Conceptual Diagram and ERD
		- What is drawn up in the plan of the database
		- Concepts for the database
		- erd being like implementation
		- conceptual being just skeletons
		- so erd is skin and organs
- Internal Schema
	- Logical Schema
		- Transforming the ERD to relational schema and applying normalisation
		- Does this mean the ERD is completely non-normalised...
	- Physical Schema
		- Organisation of physical records, choice of file organisation, indexing.
# Relational Model

- Degree is the number of attributes a model contains
- Cardinality is the number of tuples ie  number of records/rows
- Relational Database: collection of normalised relations with distinct relation names
## Logical Physical vs Implementation
### How do we model the various ways of RDBMS:

| implementation | Physical | Logical   |
| -------------- | -------- | --------- |
| Table          | File     | Relation  |
| Column         | Field    | Attribute |
| Row            | Record   | Tuple     |
### Keys

- Primary
- Foreign
- Candidate
- Composite
- Alternate
- Surrogate
- Super
### Constraints


# Conceptual Design 

## Entity/Entity type
### Rough Notes
- Entity -> Object 
- Entity type -> Class
- Entity type has at least one property to describe it
- Colour cannot be entity type (Staff is an entity type)
- Colour would be an attribute

- The requirements specification of the system is not always a textual description, it can be a vague description with a set of a dataset (csv,json,etc)
### Real Notes

#### Entity
- Person place object event or concept in the user environment about which the organisation wishes to maintain data on
#### Entity Type
- Collection of entities that share common properties or characteristics
- Singular noun
- First letter of each word in the entity name should be in upper case
![[Screenshot_20250125_000214.png]]
### Entity instance
- Each entity instance is a single occurence of an entity type

## Relationships
- Binary 
- Terinary
- One to one
- one to many
- many to one
- many to many
- 
## Conceptual Diagram

- Top level design
- Should not be used to do reverse engineering by starting with a full on ERD
- Only contains entity types and relationships
- No attributes
- Contains ternary binary quadratic etc relationships
- Can contain many to many - Remember conceptual - m:m Relationships and 
- Relationships can have some attributes/properties

- For a query composite primary key takes up more time
- Try keep to one primary id, maybe derived from two candidate keys for a surrogate key.
- Degrades the query performance
- Standard primary key really should be natural, eg a studentID.
- Surrogate key is not natural
## ER Diagram
- No many to many relationships in ERD Diagram
- Only have 1:m, 0:1 relationships. 
- ERD can only have binary or unary relationships.
- Can have attributes in each entity type.
- Cannot have properties for relationships in ERD, instead we create an associated entity type to capture those properties of the relationship.

### Associative Entity Type:

- We can use entity types to prevent many to many relationships
- Supervision is entity type in this situation

| Staff                | Supervision                              | Student                         |
| -------------------- | ---------------------------------------- | ------------------------------- |
| id                   | superviseID - Surrogate key              | id                              |
| name                 | dateSupervising                          | name                            |
|                      | room                                     |                                 |
| one staff supervises | one supervision supervises many students | many students can be supervised |


## ER vs conceptual Diagram



# Logical design and Normalisation

## Local Design

## Normalisation

## Denormalisation

# Physical Design

## Optimising Data Retrieval in MySQL

## Indexing in MySQL 

# Transaction - New topic never covered :0

ACID:
- Atomic:
	- Transaction cannot be subdivided
	- A transaction succeeds completely or fails completely
	- if a single operation fails it all should fail
	- It should be able to deal with any faults in the middle of  a transaction
	- If a transaction fails a client can safely retry.
	- in the context of nosql, atomicity is not ensured.
- Consistent:
	- Constraints do not change from before transaction to after transaction
	- Ensures that the transaction brings the database form a valid state to another valid state
	- It is a property of the application not of the database 
	- The database cannot enforce application specific invariant
	- In the context of nosql it is often not discussed
- Isolated:
	- Database changes are not revealed to users until after the transaction has been completed
	- Concurrently executed transactions are isolated from each other in that we need to deal with concurrent transactions that access the same data
	- High levels of isolation where each transaction occurs as if it was the only transaction on the database
### Isolation detail
- Read committed: No dirty reads or writes, no reads or writes can be performed until data has been committed
- Many SQL databases implement key levels of isolation
- NO dirty writes prevent all inconsistencies.
  some inconsistencies can be seen with no dirty reads.
- Durable:
	- Database changes are permanent
	- Ensures that once a  transaction has been committed fully, data cannot be lost
	- Even if a server crashes
	- In the context of nosql durability is also ensured

- Having such properties makes the life of devs easy but ACID properties are not the same in all databases 
	- It may not even be the same in SQL databases
- NoSQL solutions tend to provide weaker safety guarantees