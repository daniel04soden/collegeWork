- **Sharding**, split a large set of data into multiple logical grouped chunks of data and distribute those chunks evenly across multiple servers
- Basic unit of storing data in multiple servers is **chunks**. Each has its own read and write operations
- Clusters include replication and sharding while balancing read and write operations
- Cluster two important operations:
	- Splitting/sharding - fast and cheap
	- Migration - expensive and slow (we need to try avoid migration once all data properly split, however has to be done at the start)
- Clusters have three components
	1. Shard
	2. Config server
	3. Interface/Router
	- Shard - data store of the cluster containing chunks of application data. A shard is a replica set without arbiters. 
	- Chunk size 128mb in 8.0 - min size is 1mb
	- If application data is less than default size of chunk, shrink size to see action of splitting
	- Primary shard - Each database must have one primary shard, this contains both sharded and unsharded collections. In mongodb the first shard is automatically chosen as the primary shard. You can have an unlimited number of shards in a cluster. Just depends on the application needs
	- Shard key - set of fields whose values are used to logically group documents into a single chunk. In other words, it is used to separate documents into different shards.
	- Config server - A node which does not connect with shards but direct connects with interfaces (mongos in mongodb). There are only three nodes for the config server. This is because of the two-phase commit. The config server does not contain any application data. The config server contains authentication information, cluster settings and metadata.
	- If the config server has only one node and there is a hardware failure in this node, the cluster still functions as normal however the application/user can access wrong data from the wrong shards. This is due to the interface using an old copy of the metadata (remember in simple terms metadata is data about other data.)
	- If the config server has more than three nodes the replication lag could cause the inconsistency of metadata in those spared nodes if there is a failover; it also wastes the resources and introduces extra workload for the balancer in the cluster. 
	- The interface is a lightweight access point between the application and the cluster. The application cannot directly access the data in the shards, it must be connected with the config server. It does not contain any application data. It must have a cache of the most up to date metadata. In other words it directly gets a copy of the metadata from the config server. The interface only fetches the data 
	- The metadata in mongodb is a routing table that directs which shards contains which chunk of data. The metadata must be up-to-date and consistent. 