const mongoose = require("mongoose");
const users = require("./userSchema")

const bookSchema = new mongoose.Schema({
	userID: {
		type: Number,
		required: true,
	},
	name: {
		type: String,
		required: true,
	},
	date: {
		type: Date,
		required: true,
		default: Date.now,
	},
	time: {
		type: String,
		required: true,
	},
	cardNumber: {
		type: Number,
		required: true,
	},
	expiryDate: {
		type: Date,
		required: true,
	},
	securityCode: {
		type: String,
		required: true,
	},
	trainingType: {
		type: String,
		required: true,
		default: "Cardio",
	}
});

const collection = mongoose.model("booking", bookSchema);

module.exports = collection;
