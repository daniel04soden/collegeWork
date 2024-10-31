const mongoose = require('mongoose'); // Exporting over mongoose
const connect = mongoose.connect('mongodb://localhost:27017/training'); // Connecting to the local db

connect.then(() => {console.log('Connected to db !');});

connect.catch(() => {console.log('Cannot currently connect to db')});

// Creating our schema:

const schema = new mongoose.Schema(
    name:{
        type: String,
        required: true
    }
    email:{
        type: String,
        required: false
    }
    age:{
        type:Number,
        required: true
    }
    password:{
        type:String,
        required: true
    });

const collection = new mongoose.model("users",schema);

module.exports = collection;
