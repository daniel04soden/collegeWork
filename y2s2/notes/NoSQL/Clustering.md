# Questions:
# Clusters
- Clusters are composed of three main components:
  1. Shards
  2. Config server
  3. Interface aka router - mongos
  
- **What is a shard?**
- A shard is a subset of the sharded data

- Shards are the only place where the application data gets saved in a sharded cluster

- Shards are mainly used for improved query performance and horizontal scaling
  of a large amount of data
  
- In our cluster we're going to have 12 shards in total, 3 for each region in the USA.

-  **What is a config server?**

- Replica set that persistently stores metadata and config settings in the sharded cluster.
- Config servers persistently store meta data relating to the sharded cluster
- We need Three nodes in our config server
- Having three as opposed to one leads to:
	- Less consistency for other servers/shards 
	- No redundancy - ie data isn't pointlessly duplicated
- The config server does hold data, it holds role based access control data ie auth settings for the cluster. It also holds lists of chunks on every shard and ranges that define these chunks
-  **What is an interface in a cluster?**
- The interface acts as a query router providing an interface between client applications and the shardded cluster. It routes our queries based on the shard key, ensuring that the clinet applicaitons interact with the cluster as if it were a single mongodb instance.
- Allows for seamless scaling scaling and management of large datasets across multiple shards without requiring changes to the application code.
- It does not hold data itelf, instead it aggregates and merges results from multiple shards to provide a unified response to the client. It maintains a cache of the cluster's metadata such as the shard key ranges and location of data to route queries 
- We should have at least 2 mongos instances are reccomended for failover portection reasons, like in our script, if there is a high load of data 3 or more could also be a good shout however the maximum is 30.
- The main problem with having only one interface for the cluster, is that we only have a single point of failure so if that interface goes down, we have no way for the queries to be routed from the shardded clsuter to the application and users will not be able to access their data.

# What is a chunk?

- Chunks are the basic unit of data stored in multiple servers ie in this case the data stored in shards. Each chunk has their own read and write operations. Chunks help distribute data evenly across the shards in a sharded cluster to ensure balanced data distribution and efficient query processing
- To change the size we connect to our interface, switch to config database and use this command:
```js
db.settings.updateOne({_id:"chunksize"},{$set:{_id:"chunksize",value:1}},{upsert:true})
```
# Demonstrate the cluster is fully functioning
- Do in video
- In MongoDB, a shard key is a field or a combination of fields that defines the distribution of data across shards in a sharded cluster
# Explain how the cluster shards a collection
- A cluster shards our collection by paritioning the data into smaller **chunks** and distributing these chunks across multiple shards.
- We specify a shard key as seen below where I shard and set an index on income, general health and bmi as they are the most frequently queried metrics in our database and have a high level of cardinality ie it should have many possible values to ensure an even distribution of data across shards, there is a large level of variation of data from 
```js
res = sh.shardCollection("diabetes.healthIndicators",{"Income":hashed,"GenHtlth":1,"BMI":1})
```
#  Explain the process of migration

# Comparing to sharding, which process we should avoid and why
- We should avoid migration as depending on the amount of data being migrated, the migration may affect the overall cluster operations

-  **Explain the process of splitting**

- Splitting a chunk in a sharded cluster involves dividing a large chunk of data from a shard into two smaller chunks to better distribute the data across the shard. This is needed when a chunk exceeds its max chunk size or when manually splitting chunks to improve data distribution
```js
sh.splitAt( "diabetes.healthIndicators",{"Income":{$lte:3},"BMI":{$gte:20})
```
# Main queries:

```js
// First query 
db.healthIndicators.find({"Sex":0,"Age":{$lt:3},"BMI":{$gte:18,$lte:24},"Education":6,"AnyHealthcare":1,"GenHlth":{$gte:4},"Smoker":0,"Diabetes_012":0,"PhysActivity":1,"Fruits":1,"Veggies":1,"Income":{$gt:6,$lte:7}},{_id:0})


```

```js
// Second query
db.healthIndicators.countDocuments({"Sex":1,"Age":{$gt:9},"Income":{$lte:3}, "GenHlth":{$gte:4},"NoDocbcCost":0,"AnyHealthcare":0,"DiffWalk":1,"MenHlth":30})
```

```js
// Third query
 db.healthIndicators.aggregate([
  {
    $match: {
      Smoker: 1,
      Diabetes_012:{$in:[1,2]},
      Education: 1,
      Income: { $lte: 5 },
      GenHlth: 5,
      HighBP: 1,
      HighChol: 1,
      Stroke: 1,
      HeartDiseaseorAttack: 1,
    },
  },
  {
    $group: {
      _id: {
        AgeGroup: "$_Age", 
        BMIScale: "$BMI",
      },
      individuals: {
        $push: {
          Sex: "$Sex",
          Age: "$_Age", 
          BMI: "$BMI",
          Smoker: "$Smoker",
          Education: "$Education",
          Income: "$Income",
          Diabetes: "$Diabetes_012",
          GeneralHealth: "$GenHlth",
        },
      },
    },
  },
]).pretty();
```
# Atlas Queries:

```sh
mongo "mongodb+srv://diabetesdb.lq18vup.mongodb.net/" --username user
--password pass
```

Query 1:

```js
var session = db.getMongo.startSession(); // Needs to be properly fixed...
session.startTransaction({ "readConcern": { "level": "snapshot" }, "writeConcern": { "w": "majority" }})
try{
db.diabetic.updateOne({_id:"","Sex":1,"HvyAlcoholConsump":1},{$set:{bloodAcl:0.08}})
session.commitTransaction();
print("Done transaction")
}catch(e){
	session.abortTransaction();
	print("Transaction Failed")
}
```

```js
var db = db.getSibling("diabetic")
var session = db.getMongo.startSession();
sesh2.startTransaction({ "readConcern": { "level": "snapshot" }, "writeConcern": { "w": "majority" }})
try{
db.diabetic.updateOne({_id:"","Sex":0,"GenHlth":{$gt:4},"PhysActivity":1},{$set:{hoursExercised:3}});

db.diabetic.updateOne({_id:"","Sex":1,"GenHlth":{$gte:3}},{$inc:{"BMI":11}});

db.commitTransaction();
print("W transaction :)")
}catch (e){
	db.abortTransaction();
	print("Transaction failed L :(")
}
```