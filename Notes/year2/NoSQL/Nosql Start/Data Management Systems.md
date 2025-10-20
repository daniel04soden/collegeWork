# Hierarchal Data management systems

- A root node links to the top layer of data nodes or record. These top
  layer records can have child records that contain adiditonial data
  about the parent record

- Entities are organised into parent child relationhsips

- Searching is more efficient.

- May duplicate data and when this data is duplicated other data may not
  be updated

- Error potential

# Nework data mangement System

- Two essential components: A schema and the database itself

- A network or graph is made up of data records linked together

- Each link represents one to many relationships

- It allows for many parents

- You can\'t have cycles in the graph

- A schema is the kind of node that can link to other nodes

- The database is where the actual data is stored

- DIfficult to design and maintain

- Changes amy require changes in the wat the data is retrieved or
  updated

# Features of RDBMS

- Based on relational model ie relationships

- Enforced schema

  - Each entry has the same amount of attributes and the data type fo
    each attribute has to be preserved
  - Integrity contrains can be psoted to restrict the attribute domain

- Normalisaiton minimiuses redundant data

- Assumptions made about RDBMS:

  - End user will interact directly with the database
  - A RDBMS manages concurrency and integrity
  - Access patterns unknown \## Benefits

- Flexibility

- Data integrity

- Avoids duplication

- Easier to maintain seucrity

- Standardised model \## Drawbacks

- Impedence mismatch:

- A mismatch between relational model and OOP model

# Rigid schema

- Big data introduces a vast amount of unstructured data in the scene
- Data is no nlonger just about text and numbers
- The info is now linked and consists of multiple data types

## Scale up and scale out

### Scale out

- RDBMS is not desinged all for scaling out because of the CAP theory
- The ACID translation model of the RDBMS cannot work on a distributed
  system

### Scale up

- If the data is restricted to be structured data the vast amount of
  data to be stored presents a problem itself for the rdbms database
- With our data sets growing with not control for the performance of the
  database decreases dramatically

## Attack of the clusters

- A shift from scaling up to scaling out
- With explosion of data, the volume of the computer architectures based
  on clusters of commodity hardware emerged as the only solution but
  relational db are not designed to run on clusters
- The mismatch between relational databases and clusters led some orgs
  to consider alternative solutions to data storage.

# Why use nosql

- Driver needs
- Technological needs
- etc

# Nosql overview

- Implement distributed state

- Main idea: Give up some of the ACID constraints to improve performance

- Simple interface

  - Write is put - Write all replicas
  - Read is get - May get only one

- Eventual consistency - Strong consistency

- It comes from not only sql it can be referred to as everything sql is
  not

- The whole point of seeking alternatives is that you need to solve a
  problem that RDBMS are a bad fit for

- Need to handle larger data volumes

- Relational what data consists of?

- NoSQL - How to query?

# Clustering

- TO cope with increase in data and traffic required more computing
  resources
- Relational dbs could be run as separate servers for diff sets of data
  - Seperate the laod
  - All shared to be controlled by ap
  - Losoing queirrinyn g
  - Licencing costs high

## Ployglot persistence

- When storing data it is best to use multiple data storage
  technologies, chosen based on the way applications or components of a
  single app is using the data. Different kinds of data are best dealt
  with in different data stores
- in short it means picking the right tool for the right job
