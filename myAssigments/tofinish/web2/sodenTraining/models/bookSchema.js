const mongoose = require('mongoose')

const bookSchema = new mongoose.Schema(
    {
        userID: {
            type: String,
            required: true,
        },
        name:{
            type: String,
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