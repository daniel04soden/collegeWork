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
- ![[Screenshot_20250122_160748.png]]
# Logical design and Normalisation

## Local Design

## Normalisation

## Denormalisation

# Physical Design

## Optimising Data Retrieval in MySQL

- When you execute a select statement Mysql uses the query optimiser to analyse the statement and perform the query as effectively as possible
- The optimiser tries to use indexes whenever possible to process the statement
- This tries to determine the greater number of rows that can be eliminated from the search
- You need to specify that MYSQL should not be using its cache so that we can accurately judge the time these tasks take to complete.

- Before indexing to optimise performance you firstly use the explain statement to analyse how many rows have been scanned to answer the query
- Another command for this is OPTIMISE table, using defragmentation. This doesn't actually make queries faster ut we use it when we have no choice. For exxample if we are working with a table that constantly dxperiences updates.
- You might have to use optimise table to ensure the table stats are accurate when read by the query optimiser.
- Syntax of optimise table statement

``` SQL
OPTIMIZE TABLE conferences
```

- Do not use needed wild cards in LIKE clauses 
- Isolate indexed columns in comparison expressions. Mysql cannot use an index on a column if that column appears as an argument in a function or arithmetic expression
- We pick the columns involved in either where clause or having clause for indexing.
- Do not use columns involved in calculations for indexing
``` SQL
SELECT id FROM data
USE INDEX(type)
WHERE TYPE = 12345 AND level > 3
ORDER BY id;
```
## Indexing in MySQL 

- On existing tables
``` SQL
ALTER TABLE conferences ADD INDEX loc_index(location_id,topic_id);
```

- Rerun the explain statement after adding the index

``` SQL
CREATE UNIQUE INDEX orderID,uidx
USING BTREE ON order(orderID DESC, modelID);
```

- Do not index columns that only take a few columns
- So 1-4, gender, etc
# Transaction - New topic never covered :0

