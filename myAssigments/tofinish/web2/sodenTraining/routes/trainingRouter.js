const express = require("express");
const trainingRouter = express.Router();
const users = require('../models/userSchema')
const bookings = require('../models/bookSchema')
const encryption = require('bcrypt')

trainingRouter
  .route("/")
  .put((req, res, next) => {})

trainingRouter.route("/book").get((req, res, next) => {
  res.render("book.ejs", { title: "Book a session" });
  console.log("Routed to booking page");
})
.post(async (req,res) => {
  // Identifying user from id
    const userData = await users.findOne({id : req.body.id})

    // Storing booking information
    const bookingData = {
      userID:req.body.id,
      name: userData.name,
      date:req.body.date,
      time:req.body.time,
      cardNumber:req.body.cardNumber,
      expiryDate:req.body.expiryDate,
      securityCode: await encryption.hash(req.body.cvc,10)
    }
    
    if (userData != []) {
      const bookingConfirmation = await bookings.insertMany(bookingData)
    } else {
     res.send('Invalid id') 
    }
});

trainingRouter.route("/manage")
  .get((req, res, next) => {
    res.render('managing.ejs', {title: 'Manage Your Session' });

  })
  .post(async (req,res) => {
    const bookingInfo = await bookings.findOne({userID:req.body.idCheck});

    if(bookingInfo != null){
      res.render("display-book.ejs",{title: "Bookings", bookingInfo :bookingInfo})
    }else{
      res.send('Unknown id try again!')
    }
    
  })
  .delete(async (req, res, next) => {
    const deletion = await bookings.deleteOne({bookingID:req.body.bookIDCheck})
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
