const mongoose = require('mongoose')

const bookSchema = new mongoose.Schema(
    {
        userID: {
            type: Number,
            required: true,
            unique: true
        },
        name:{
            type: String,
            unique:true
        },
        date:{
            type:Date,
            required:true,
            default: Date.now
        },
        time:{
            type:String,
            required:true,
        },
        cardNumber:{
            type:Number,
            required:true
        },
        expiryDate:{
            type:Date,
            required:true
        },
        securityCode:{
            type:String,
            required: true
        }

    }
)

const collection = mongoose.model("booking", bookSchema);

module.exports = collection;