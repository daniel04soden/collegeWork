# Properly explain what a shard is, what a shard is used for

- A shard is a subset of the sharded data
- Shards are the only place where the application data gets saved in a sharded cluster
- Shards are mainly used for improved query performance and horizontal scaling
  of a large amount of data

# How many shards we should have in a cluster?

- We should have 12 shards

# What is a config server?

- Replica set that persistently stores metadata and config settings in the sharded cluster

# How many nodes we should have in a config server?

- Three nodes

# What does a config server do?

- Config servers persistently store meta data relating to the sharded cluster

# What problem we will have if the config server has only one node?

- Less consistency for other servers/shards
- No redundancy

# Does the config server hold any data? If it does, what kind of data it should have?

- Yes it does it holds role-based access control ie who can access what and authetication settings for the cluster.
- On top of this it holds lists of chunks on every shard and ranges that define the chunks

# What is an interface in a cluster?

- Provides connectivity between application and the cluster eg Mongos

# Does interface have any data? If it does, what kind of data it holds?

-

# How many interfaces we should have in a cluster?

# What problems will we have if there is only one interface for the cluster?

# What is a chunk?

# How to change the size of a chunk in the cluster?

# Demonstrate the cluster is fully functioning

# Explain how the cluster shards a collection

# Explain the process of migration

# Comparing to sharding, which process we should avoid and why

# Explain the process of splitting

# Use an example to explain how the splitting work

# Explain what is a shard key

# Justify your choice of shard key

## Clearly demonstrate that Altas cluster is properly set up and connected with mongosh successfully - Not a question
