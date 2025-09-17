const mongoose = require('mongoose');
const Schema = mongoose.Schema;



//data sent to DB and received from DB for table creation
var Schemaful = new Schema({
    name: {
        type: String,
        required: true,
    },
    timeEducation: {
        type: Number,
        required: true,
        "maximum": 1140
    },
    timeShopping: {
        type: Number,
        required: true,
        "maximum": 1140
    },
    timeBrowsing: {
        type: Number,
        required: true,
        "maximum": 1140
    },
    timeSocial: {
        type: Number,
        required: true,
        "maximum": 1140
    },

}, {
    timestamps: true
});
var phone = mongoose.model('Phone', Schemaful); //initialize a model with a scheme you created

module.exports = phone;