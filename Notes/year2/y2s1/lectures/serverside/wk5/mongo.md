# Mongodb - NoSQL Databases

- SQL are best for applications with complex queries and transactions

  - Non sql is better for flexibility and scalability.

## Four borad categories

- Document db - Mongodb
- Key-value databases - Redis
- Column-family databases- Cassandra

### Document databases

- Document: Self contained piece of info

  - Collection: Set of documents
    - DB = A set of collections

- Document database

  - Server can support many databases
  - BSON can be used as well as JSON
  - Supports length prefix, type info of a field value
    - Primitivies types not supported by raw JSON like date time, raw
      binary and ObjectID.

## Mongodb objectid

- Each document has an id \"\_id\" field that is unique

<!-- -->

- Default objectid created by Mongo when you insert a document
