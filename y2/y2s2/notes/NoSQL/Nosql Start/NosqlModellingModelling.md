# NoSQL Modelling

# Why go from rdbms to nosql
1. Easier to scale
2. Better performance
3. 40x lower cost
4. Greater agility
5. Cross platform

# How to get started

1. Identify right application
2. Model data
3. Access your data
4. Migrate your data


## Identifying the right application

- Have one or more requirements:
    - Send and receive JSON
    - Store tbs of data
    - Be available 24/7
    - Read and write to multiple data centers

# Strategies and best practices

- if one to one store as nested objects
- Many to one or many to many - different documents
- Data reads are mostly parent fields - different documents

## Atomicity and NoSQl

- NoSQL doesnt support atomicity that spans multiple aggregates
- This means that if we need to update multiple aggregates we have to manage that in the app code 
