# Characteristics
- Schemeless Design: NoSQL databases typically do not enforce a fixed schema.
	- This allows devs to insert data without first defining its structure, making them more adaptable
- Variety of data models
- Horizontal Scalability
- Distributed Architecture
- Dynamic and Unstructured data 

## Benefits:
- Efficiency 
- Scalable
- Availability
- Flexible models
- Replication
- Distribution
- Storage
- Consistency

## Issues
### Lack of
- Schema 
- Normalization
- Transactions
- Consistency
- integrity constrains
- Standard language


## Challenges and considerations
- Consistency and ACID compliance trade offs
	- NoSQL databases sacrifice strict consistency for svcalbility. Devs need to consider the trade offs in terms od ata consistency and acid compliance
- Learning curve
	- Devs are accustomed to RDBMS
- Data Migration Challenge:
	- Moving from RDBMS to NoSQL requires large migrations issues


# Types of  NoSQL dbs

## Pure key value
- Redis
- Riak
- LevelDb

## Document based
-Mongodb
- Couchdb
- RavenDB


## Column
- Data in columns rather than rows
- Grouped into  familiies and each family can have a different set of columns
- Well  suited for heavy read write workloads

- Examples
    - Amazon 
    -SimpleDB

## graphdb
- DAG
- Designed for handling relationships between data points
- Represented as nodes edges and properties

- Examples:
    -neo4j



    - infinite graph



