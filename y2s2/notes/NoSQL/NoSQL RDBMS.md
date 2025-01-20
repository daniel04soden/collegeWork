# RDBMS


## Three Tier Architecture

- Frontend
- Middle ware
- Backend
# Conceptual Design 

## Entity/Entity type

- Entity -> Object 
- Entity type -> Class
- Entity type has at least one property to describe it
- Colour cannot be entity type (Staff is an entity type)
- Colour would be an attribute

- The requirements specification of the system is not always a textual description, it can be a vague description with a set of a dataset (csv,json,etc)
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

- Write notes from guide slide

|     |     |     |
| --- | --- | --- |
|     |     |     |

# Logical design and Normalisation

## Local Design

## Normalisation

## Denormalisation

# Physical Design

## Optimising Data Retrieval in MySQL

## Indexing in MySQL 

# Transaction - New topic never covered :0

