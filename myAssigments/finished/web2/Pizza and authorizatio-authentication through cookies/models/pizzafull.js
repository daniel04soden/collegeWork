const mongoose = require('mongoose');
const Schema = mongoose.Schema;

require('mongoose-currency').loadType(mongoose);
var Currency = mongoose.Types.Currency;

var toppingSchema = new Schema({
    topping:  {
        type: String, 
        enum: ['peppers', 'mushrooms', 'onions', 'capers', 'pinnapple', 'olives','ham', 'pepperoni', 'anchovies'],
        required: false
    },
    
}, {
    timestamps: true
});


var pizzaSchemaful = new Schema({
    name: {
        type: String,
        required: true,
        unique: true
    },
    description: {
        type: String,
        required: true
    },
    image: {
        type: String,
        required: true
    },
    category: {
        type: String,
        required: true
    },
    price: {
        type: Currency,
        required: true,
        min: 0
    },
    toppings:[toppingSchema]
}, {
    timestamps: true
});
var pizzas = mongoose.model('Pizza', pizzaSchemaful);

module.exports = pizzas;