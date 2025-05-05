//----------------------------------------------------
//
//  5. Set up the Shards
//
//----------------------------------------------------
//
//------------------------------------------------
// 5.1. Variables
//------------------------------------------------
//
db = db.getSisterDB("config");
var mongosConn = db;
var res = null;
//
//------------------------------------------------
// 7.2. 
//------------------------------------------------
//
db.settings.save( { _id:"chunksize", value: 1 } )
//
//------------------------------------------------
// 5.2. 
//------------------------------------------------
//
// 5.2.1. 
//
db = connect("localhost:27000/my_database");
//
// 5.2.2. 
//
db = mongosConn;
res = sh.addShard("north/localhost:27000");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("north/localhost:27000");
    }
}
print("north Added!");
//
//------------------------------------------------
// 5.3. 
//------------------------------------------------
//
// 5.3.1. 
//
db = connect("localhost:27100/my_database");
//
// 5.3.2. 
//
db = mongosConn;
res = sh.addShard("south/localhost:27100");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("south/localhost:27100");
    }
}
print("south Added!");
//
//------------------------------------------------
// 5.4. 
//------------------------------------------------
//
// 5.4.1. 
//
db = connect("localhost:27200/my_database");
//
// 5.4.2. Add the shard
//
db = mongosConn;
res = sh.addShard("east/localhost:27200");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("east/localhost:27200");
    }
}
print("east Added!");
//
//------------------------------------------------
// 5.5. 
//------------------------------------------------
//
// 5.5.1. 
//
db = connect("localhost:27300/my_database");
//
// 5.5.2. 
//
db = mongosConn;
res = sh.addShard("west/localhost:27300");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("west/localhost:27300");
    }
}
print("west Added!");
//
//------------------------------------------------
// 5.6. Quit
//------------------------------------------------
//
quit()
