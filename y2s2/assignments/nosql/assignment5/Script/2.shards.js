//----------------------------------------------------
//  Set up the Shards
//----------------------------------------------------

db = db.getSiblingDB("config");
var mongosConn = db;
var res = null;

res = sh.addShard("north/localhost:27000,localhost:27001,localhost:27002");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("north/localhost:27000,localhost:27001,localhost:27002");
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
//
// 5.3.2. 
//
db = mongosConn;
res = sh.addShard("south/localhost:27100,localhost:27101,localhost:27102");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("north/localhost:27100,localhost:27101,localhost:27102");
    }
}
print("south Added!");
//
//------------------------------------------------

db = mongosConn;
res = sh.addShard("east/localhost:27200,localhost:27201,localhost:27202");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("east/localhost:27200,localhost:27201,localhost:27202");
    }
}
print("east Added!");

//
// 5.5.2. 
//
db = mongosConn;
res = sh.addShard("west/localhost:27300,localhost:27301,localhost:27302");
while (res.ok != 1){
    sleep(60);
    if (res.ok != 1){
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("west/localhost:27300,localhost:27301,localhost:27302");
    }
}
print("west Added!");
sleep(60)
// 5.4. 
//------------------------------------------------
//
// 5.4.1. 
//
//
// 5.4.2. Add the shard
//
//
//------------------------------------------------
// 5.5. 
//------------------------------------------------
//
// 5.5.1. 
//
//
//------------------------------------------------
// 5.6. Quit
//------------------------------------------------
//
db.getSiblingDB("config").settings.updateOne({_id:"chunksize"},{$set:{_id:"chunksize",value:1}},{upsert:true})
quit()
