db.students_usa.aggregate([
  {
    $match:
    {$expr:
     {$and:
      [
        {$gte:[{$arrayElemAt:["$scores.score",0]},40]}
        {$gte:[{$arrayElemAt:["$scores.score",1]},40]}
        {$gte:[{$arrayElemAt:["$scores.score",2]},40]}
      ]
     }}
  },
  {
    $group:{
      {
        _id:"$region",
        count:{$sum:1},
      },
    },
  },
]);
