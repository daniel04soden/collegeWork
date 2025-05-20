//----------------------------------------------------
//
//  7. Shard the collection
//
//----------------------------------------------------

db = db.getSiblingDB("diabetes");
var res = null;


res = db.healthIndicators.createIndex({ "Income": 1, "Age": 1 , "BMI":1}); 

/*
while (res.ok != 1) {
  sleep(10);
  if (res.ok != 1) {
    print("Creating index for health indicators collection failed. Trying it again");
    res = db.healthIndicators.createIndex({ "Income": 1, "Age": 1 , "BMI":1});
  }
}
*/

print("healthIndicators Collection Index Created!");


res = sh.shardCollection("diabetes.healthIndicators", { "Income": 1, "Age": 1 , "BMI":1}); 
/*
while (res.ok != 1) {
  sleep(10);
  if (res.ok != 1) {
    print("Sharding healthIndicators collection failed. Trying it again");
    res = sh.shardCollection("diabetes.healthIndicators", { "Income": 1, "Age": 1 , "BMI":1}); 
  }
}
*/

print("health Indicators Collection Sharded!");

for (i = 0; i < 20; i++) {
  sh.status();
  sleep(10000);
}

quit();
