var mongo = require('mongodb').MongoClient;
mongo.MongoClient.connect("mongodb://localhost:27017/",function(err,mongo) {
    if (err) throw err;
    let myDb = Db.db("myDb");
    

}

)


