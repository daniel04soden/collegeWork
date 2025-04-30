#!/bin/bash
#
#-------------------------------------------------#
#						   #
# 1.                                  	   #
#						   #
#-------------------------------------------------#

mkdir logFiles

mkdir north0 # Substitute with Dublin
mkdir north1
mkdir north2
mkdir south0 # Cork
mkdir south1
mkdir south2
mkdir east0 # Galway
mkdir east1
mkdir east2
mkdir west0
mkdir west1
mkdir west2 # Limerick
#
# 1.3.
#
mongod --shardsvr --replSet north --dbpath north0 --port 27000 --logpath logFiles/node1.log --fork >>logFiles/log.file
mongod --shardsvr --replSet north --dbpath north1 --port 27001 --logpath logFiles/node5.log --fork >>logFiles/log.file
mongod --shardsvr --replSet north --dbpath north2 --port 27002 --logpath logFiles/node6.log --fork >>logFiles/log.file
mongod --shardsvr --replSet south --dbpath south0 --port 27100 --logpath logFiles/node2.log --fork >>logFiles/log.file
mongod --shardsvr --replSet south --dbpath south1 --port 27101 --logpath logFiles/node7.log --fork >>logFiles/log.file
mongod --shardsvr --replSet south --dbpath south2 --port 27102 --logpath logFiles/node8.log --fork >>logFiles/log.file
mongod --shardsvr --replSet east --dbpath east0 --logpath logFiles/node3.log --port 27200 --fork >>logFiles/log.file
mongod --shardsvr --replSet east --dbpath east1 --logpath logFiles/node9.log --port 27201 --fork >>logFiles/log.file
mongod --shardsvr --replSet east --dbpath east2 --logpath logFiles/node10.log --port 27202 --fork >>logFiles/log.file
mongod --shardsvr --replSet west --dbpath west0 --logpath logFiles/node4.log --port 27300 --fork >>logFiles/log.file
mongod --shardsvr --replSet west --dbpath west1 --logpath logFiles/node11.log --port 27301 --fork >>logFiles/log.file
mongod --shardsvr --replSet west --dbpath west2 --logpath logFiles/node12.log --port 27302 --fork >>logFiles/log.file
#
#-------------------------------------------------#
#						  #
# 2.                                            #
#						  #
#-------------------------------------------------#
#
# 2.1.
#
mkdir cfg0
mkdir cfg1
mkdir cfg2
#
# 2.2.
#
mongod --configsvr --replSet cfg --dbpath cfg0 --port 26050 --logpath logFiles/node13.log --fork >>logFiles/log.file
mongod --configsvr --replSet cfg --dbpath cfg1 --port 26051 --logpath logFiles/node14.log --fork >>logFiles/log.file
mongod --configsvr --replSet cfg --dbpath cfg2 --port 26052 --logpath logFiles/node15.log --fork >>logFiles/log.file
#
# 2.3. Wait for 20 seconds before continuing
#
sleep 20s
#
#---------------------------------------------------#
#						    #
# 3.                                                #
#						    #
#---------------------------------------------------#
#
mongo --port 26050 --shell 1.replica_sets.js
#
#-------------------------------------------------#
#						  #
# 4.                          		  #
#						  #
#-------------------------------------------------#
#
# 4.1.
#
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --logpath logFiles/mongos0.log --fork >>logFiles/log.file
#
# 4.2.
#
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --port 26061 --logpath logFiles/mongos1.log --fork >>logFiles/log.file
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --port 26062 --logpath logFiles/mongos2.log --fork >>logFiles/log.file
mongos --configdb cfg/localhost:26050,localhost:26051,localhost:26052 --port 26063 --logpath logFiles/mongos3.log --fork >>logFiles/log.file
#
# 4.3. Wait for 10 seconds before continuing
#
sleep 10s
#
#---------------------------------------------------#
#						    #
# 5.                    			    #
#						    #
#---------------------------------------------------#
#
mongo --shell 2.shards.js
#
#---------------------------------------------------#
#						    #
# 6.         				    #
#						    #
#---------------------------------------------------#
#
# 6.1.
#
mongoimport --db diabetes --collection healthIndicators --drop --file diabetes_012_health_indicators_BRFSS2015.csv
#
# 6.2. Wait for 10 seconds before continuing
#
sleep 10s
#
#---------------------------------------------------#
#						    #
# 7.                             		    #
#						    #
#---------------------------------------------------#
#
mongo --shell 3.shard_collection.js
#
