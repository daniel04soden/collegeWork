db.titanicCollection.find({sex:'female',survived:1},{"name":1,_id:0}).sort({"name":1})
db.titanicCollection.find({age:{$lte : 18.0},survived:1},{pclass:1,_id:0,name:1}).sort({pclass:1})
db.titanicCollection.find({age:{$lte : 18.0},survived:0},{pclass:1,_id:0,name:1}).sort({pclass:1})
