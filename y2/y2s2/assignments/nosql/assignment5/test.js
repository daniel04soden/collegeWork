use diabetesdb;
const session = db.getMongo.startSession();
session.startTransaction({ "readConcern": { "level": "snapshot" }, "writeConcern": { "w": "majority" }})
try{
db.diabetic.updateOne({_id:ObjectId("6823347dbf82b5c78aa852c3"),"Sex":1,"HvyAlcoholConsump":1},{$set:{bloodAcl:0.08}})
session.commitTransaction();
print("Done transaction")
}catch(e){
	session.abortTransaction();
	print("Transaction Failed")
}
