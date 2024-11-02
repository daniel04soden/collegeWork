const mongoose = require('mongoose')

const bookSchema = new mongoose.Schema(
    {
        userID: {
            type: Number,
            required: true,
            unique: true
        },
        date:{
            type:Date,
            required:true,
            default: Date.now
        },
        time:{
            type:String,
            required:true,
            default:'12:30 PM'
            
        }

    }
)

const collection = mongoose.model("booking", bookSchema);

module.exports = collection;