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
        required: true
    },
    email: {
        type: String,
        required: false
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
            required: true
        },
        date:{
            type:String,
            required:true
        },
        time:{
            type:String,
            required:true
        }

    }
)

const User = mongoose.model('User', userSchema);
const Book = mongoose.model('Book', bookSchema);

module.exports = { User, Book };
