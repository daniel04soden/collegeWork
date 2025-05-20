// First query 
db.healthIndicators.find({"Sex":0,"Age":{$lt:3},"BMI":{$gte:18,$lte:24},"Education":6,"AnyHealthcare":1,"GenHlth":{$gte:4},"Smoker":0,"Diabetes_012":1,"PhysActivity":1,"Fruits":1,"Veggies":1,"Income":{$gt:6,$lte:7}},{_id:0})

// Second query

db.healthIndicators.countDocuments({"Sex":1,"Age":{$gt:9},"Income":{$lte:3}, "GenHlth":{$gte:4},"NoDocbcCost":0,"AnyHealthcare":0,"DiffWalk":1,"MenHlth":30})

// Third query

db.healthIndicators.aggregate([])
