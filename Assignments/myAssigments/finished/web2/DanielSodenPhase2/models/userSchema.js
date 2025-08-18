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
        type: Number,
        required: true,
        unique: true
    }
});

const collection = mongoose.model("Users", userSchema);

module.exports = collection;

