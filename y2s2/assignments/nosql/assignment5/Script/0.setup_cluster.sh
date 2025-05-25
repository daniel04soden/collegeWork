#!/bin/bash
./cleanup.sh
# In my code i use mongo as opposed to mongo as mongo is deprecated and I am using the latest version 8.0.9

mkdir logFiles  # General logging directory

# Init our 12 nodes, three for each usa region (south)
 
# North nodes

mkdir usa_north0 
mkdir usa_north1
mkdir usa_north2

# South nodes

mkdir usa_south0 
mkdir usa_south1
mkdir usa_south2

# East nodes

mkdir usa_east0 
mkdir usa_east1
mkdir usa_east2

# West nodes

mkdir usa_west0
mkdir usa_west1
mkdir usa_west2 

sleep 3s

# Shard as replica sets

# 3 shards for North
mongod --shardsvr --replSet north --dbpath usa_north0 --port 27000 --logpath logFiles/node1.log --fork >>logFiles/log.file
mongod --shardsvr --replSet north --dbpath usa_north1 --port 27001 --logpath logFiles/node5.log --fork >>logFiles/log.file
mongod --shardsvr --replSet north --dbpath usa_north2 --port 27002 --logpath logFiles/node6.log --fork >>logFiles/log.file

# 3 shards for South
mongod --shardsvr --replSet south --dbpath usa_south0 --port 27100 --logpath logFiles/node2.log --fork >>logFiles/log.file
mongod --shardsvr --replSet south --dbpath usa_south1 --port 27101 --logpath logFiles/node7.log --fork >>logFiles/log.file
mongod --shardsvr --replSet south --dbpath usa_south2 --port 27102 --logpath logFiles/node8.log --fork >>logFiles/log.file

# 3 shards for East
mongod --shardsvr --replSet east --dbpath usa_east0 --logpath logFiles/node3.log --port 27200 --fork >>logFiles/log.file
mongod --shardsvr --replSet east --dbpath usa_east1 --logpath logFiles/node9.log --port 27201 --fork >>logFiles/log.file
mongod --shardsvr --replSet east --dbpath usa_east2 --logpath logFiles/node10.log --port 27202 --fork >>logFiles/log.file

# 3 shards for West
mongod --shardsvr --replSet west --dbpath usa_west0 --logpath logFiles/node4.log --port 27300 --fork >>logFiles/log.file
mongod --shardsvr --replSet west --dbpath usa_west1 --logpath logFiles/node11.log --port 27301 --fork >>logFiles/log.file
mongod --shardsvr --replSet west --dbpath usa_west2 --logpath logFiles/node12.log --port 27302 --fork >>logFiles/log.file

# Config server
mkdir cfg0
mkdir cfg1
mkdir cfg2

# Config server replica set 


mongod --configsvr --replSet cfg --dbpath cfg0 --port 26050 --logpath logFiles/node13.log --fork >>logFiles/log.file
mongod --configsvr --replSet cfg --dbpath cfg1 --port 26051 --logpath logFiles/node14.log --fork >>logFiles/log.file
mongod --configsvr --replSet cfg --dbpath cfg2 --port 26052 --logpath logFiles/node15.log --fork >>logFiles/log.file

# Open shell to be able to input queries

sleep 60s

mongo --port 26050 --shell 1.replica_sets.js 

sleep 45s

# Mongos interface being setup for logging  
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --port 26060 --logpath logFiles/mongos0.log --fork >>logFiles/log.file
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --port 26061 --logpath logFiles/mongos1.log --fork >>logFiles/log.file
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --port 26062 --logpath logFiles/mongos2.log --fork >>logFiles/log.file
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --port 26063 --logpath logFiles/mongos3.log --fork >>logFiles/log.file

sleep 30s

mongo --shell --port 26060 2.shards.js

sleep 30s

mongoimport --port 26060 --db diabetes --collection healthIndicators --type=csv --headerline --drop --file diabetes_012_health_indicators_BRFSS2015.csv


sleep 45s

mongo --shell --port 26060 3.shard_collection.js
