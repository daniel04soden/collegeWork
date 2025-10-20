# More databases

## Executing queries

- Two main methods of interactions \### DDL
- Execute query\
- executeUpdate
- Prepared statement - provides a means to create a reusable statement
  precompiled by the db.
- Processing time of an sql query consists of parsing sql string,
  checking syntax then semantics.

# Relational or NoSQL

## Relational

- Skills
- Structure
- Data model
- security
- VLDB

## NOSQL

- More data
- Highly changing data

# ORMs

- We do modelling with object oriented principles

- Inheritance

- Collections fields

- Define classes that hold data and

- Impedance mismatch

- Does not support oo

- We end up with two objects if we try to use inheritance

- One for class modelling

- One for relational tables

- It requires effor to map tables ot classes

- And as coding evolves it will lead to inconsistencies

- Hibernate jpa are two popular tools

- We don\'t write queries but we define methods with specific format

- And the orm takes care of everything

- Orms are essentially libraries that abstract a lot of manual detail
  for querying databaases

- Rather than manually finding by id or by a particular attribute
