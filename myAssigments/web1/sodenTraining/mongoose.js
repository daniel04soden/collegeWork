const mongoose = require('mongoose'); // Exporting over mongoose
// Creating our schema:

const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        unique: true
    },
    email: {
        type: String,
        required: false,
        unique: true
    },
    age: {
        type: Number,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    id: {
        type:Number,
        required:true,
        unique: true
    }
});

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

const User = mongoose.model('User', userSchema);
const Book = mongoose.model('Book', bookSchema);

module.exports = { User, Book };
