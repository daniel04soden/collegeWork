const mongoose = require('mongoose'); // Exporting over mongoose

mongoose.connect('mongodb://localhost:27017/training')
  .then(() => {
    console.log('Connected to database');
  })
  .catch((err) => {
    console.error('Error connecting to database:', err);
  });

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
