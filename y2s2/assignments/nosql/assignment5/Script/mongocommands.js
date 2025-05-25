// First query 
db.healthIndicators.find({"Sex":0,"Age":{$lt:3},"BMI":{$gte:18,$lte:24},"Education":6,"AnyHealthcare":1,"GenHlth":{$gte:4},"Smoker":0,"Diabetes_012":0,"PhysActivity":1,"Fruits":1,"Veggies":1,"Income":{$gt:6,$lte:7}},{_id:0})

// Second query

db.healthIndicators.countDocuments({"Sex":1,"Age":{$gt:9},"Income":{$lte:3}, "GenHlth":{$gte:4},"NoDocbcCost":0,"AnyHealthcare":0,"DiffWalk":1,"MenHlth":30})

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
        AgeGroup: "$_Age", // Use the correct age field name
        BMIScale: "$BMI",
      },
      individuals: {
        $push: {
          Sex: "$Sex",
          Age: "$_Age", // Use the correct age field name
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

mongo "mongodb+srv://diabetesdb.lq18vup.mongodb.net/" --username user
--password pass
