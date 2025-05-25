db = db.getSiblingDB("diabetes");

let indexCreated = false;
while (!indexCreated) {
  try {
    const indexName = db.healthIndicators.createIndex({"Diabetes_012":1,"Age":1,"GenHlth":1});  //This used to be paired with a res!=1 check but that doesn't work
    print("Index created: "+indexName);                                                         //in mongosh. I'm pretty sure it was part of mongo instead.
    indexCreated=true;
  } catch (e) {
    print("Index creation failed: "+e.message);
    sleep(5000);
  }
}

let shardingEnabled = false;
while (!shardingEnabled) {
  try {
    res = sh.enableSharding("diabetesAnalysisDB");
    print("Sharding enabled on diabetesAnalysisDB");
    shardingEnabled = true;
  } catch (e){
    print("Enable sharding failed: "+e.message);
    sleep(5000);
  }
}

let shardSuccess=false;
while (!shardSuccess){
  try {
    res = sh.shardCollection("diabetesAnalysisDB.healthIndicators",{"Diabetes_012":1,"Age":1,"GenHlth":1});
    print("Sharding of healthIndicators collection succeeded.");
    shardSuccess=true;
  } catch (e) {
    print("Sharding failed: "+e.message);
    sleep(5000);
  }
}

for (i = 0; i < 20; i++) {
    sh.status();
    sleep(10000);
}

quit();
