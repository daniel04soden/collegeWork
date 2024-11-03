const express = require("express");
const trainingRouter = express.Router();
const users = require('../models/userSchema')
const bookings = require('../models/bookSchema')

trainingRouter
  .route("/")
  .get((req, res, next) => {})
  .post((req, res, next) => {})
  .put((req, res, next) => {})
  .delete((req, res, next) => {});

trainingRouter.route("/book").get((req, res, next) => {
  res.render("book.ejs", { title: "Book a session" });
  console.log("Routed to booking page");
})
.post(async (req,res) => {
    const bookingData = {
      userID:req.body.id,
      date:req.body.date,
      time:req.body.time
    }
    const userData = await users.findOne({id : req.body.id})
    if (userData != []) {
      const bookingConfirmation = await bookings.insertMany(bookingData)
    } else {
     res.send('Invalid id') 
    }
});



trainingRouter.route("/manage").get(async ( req, res, next) => {
  const managingData = await bookings.findOne({id: req.body.idCheck});
  res.render('managing.ejs', { bookingInfo: managingData , title: 'Manage Your Session' });
  console.log("Routed to manage booking page");
});

trainingRouter.route("/contact").get((req, res, next) => {
  res.render("contact.ejs", { title: "Contact Us" });
  console.log("Routed to contact us page");
});

trainingRouter.route("/help").get((req, res, next) => {
  res.render("help.ejs", { title: "Help/Faqs" });
  console.log("Routed to help page");
});



module.exports = trainingRouter;
