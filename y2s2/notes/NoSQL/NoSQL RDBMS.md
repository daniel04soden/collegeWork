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

<<<<<<< HEAD
- Write notes from guide slide
- ![[Screenshot_20250122_160748.png]]
# Logical design and Normalisation

## Local Design

## Normalisation

## Denormalisation

- **Definition:** This is the process of adding redundant data to get rid of complex joins in order to optimise database performance.
- Increase redundancy in a database in order to improve query performance
- It would be needed in a highly queried database
- May move from BCNF to 3NF
- Data is included in one table from another in order to eliminate the second table which reduces the number of necessary joins in a query and thus achieves higher performance.

### Tips
- Get conceptual and ERD correct
- Should be done during database design
- Last choice to boost performance
- Do not implement any more denormalisation after performance reached
- DO best to learn logical design of app to understand what parts of system likely o be affected by denormalisation.
- Analyse how often data is changed
- Look at what parts of app is having performance issues
- Learn about data storage techniques

### When to denormalise 

-  Performance with structure is not acceptable
- Check on hardware used in server
- Hard to undo structural changes
- Be sure willing to trade decreased data integrity for increased performance
- Consider possible future scenario where apps may place different requirements on the data
### What is it for?
#### Storing Derivable data 
- Situations when storing derivable value is appropriate
	- When derivable values are frequently needed and when the source values are not
	- When the source values are infrequently changed
##### Advantages
- DO not need to be looked up every time the value is needed
- Calculation not peroromed
##### Disadvantages
- Data duplciation
- DML against the source data 

#### Using pre joined tables
- to pre join tables you need t add a non key column to a table when the actual value of the primary key and the foreign key has no business meaning
- By including a non-key column that has an actual business meaning you can avoid joining tables thus speeding up specific  queiries
- You need to make sure the de-normalised columns get updated each time the aster column value is altered
- This technique can be used when you have to make a lot of queries against many different tables and as long as the stale data is acceptable

#### Using Hard coded values

- If there's a reference table with constant records, you can hard code them into your app
- You don't then need to join tables to fetch the reference values
- You need to create a check constraint to validate values against references values
- This constraint must be re-written each time a new value in the table is needed
##### Advantages
- No need to implement lookup table
- No joins to a lookup table
##### Disadvantages
- Re-coding and restating are required if look up values are altered


#### Keeping details with the master
- There can be cases when the number of detail records per master is static and fixed
- There can be cases when detail records are queried with the master
- In these cases you can denormalize a database by adding detail columns to the master table
- This technique proves most useful when there are few records in the detail table

##### Advantages
- No need to use joins
- Saves space

#### Current indicator Column
- Indicator to show column at the current date
#### Repeating a single detail with its master

- When you deal with historical data many queries need a specific single most current record and rarely require other details
- With this technique you can introduce a new foreign key column for storing this record with its master
- Situations where this is appropriate:
	- When detail records per master have a property such that one record can be consiedered current and others historical
	- When the master often has only one single detail record
##### Advantages:
- No need to make joins for queries that need a single record
##### Disadvantages
- Data inconsistencies are possible as a record value must be repeated 
#### End date columns

- To store the end date for periods that are consecutive then the end date for a period can be derived form the start date.
- Current - start date  - Not this use hard coded end date.
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