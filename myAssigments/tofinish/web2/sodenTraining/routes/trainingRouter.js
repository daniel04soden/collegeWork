// Main imports

const express = require("express");
const trainingRouter = express.Router();
const users = require('../models/userSchema')
const bookings = require('../models/bookSchema')
const encryption = require('bcrypt')


// Beginning of router which handles all booking operations
trainingRouter
  .route("/") // Route to main page
  .put((req, res, next) => { })

trainingRouter.route("/book").get((req, res, next) => {
  res.render("book.ejs", { title: "Book a session" });
  console.log("Routed to booking page");
})
  .post(async (req, res) => {
    // Identifying user from id
    const userData = await users.findOne({ id: req.body.id })

    // Storing booking information
    const bookingData = {
      userID: req.body.id,
      name: userData.name,
      date: req.body.date,
      time: req.body.time,
      cardNumber: req.body.cardNumber,
      expiryDate: req.body.expiryDate,
      securityCode: await encryption.hash(req.body.cvc, 10),
      trainingType: req.body.trainingType
    }

    if (userData != []) {
      const bookingConfirmation = await bookings.insertMany(bookingData)
    } else {
      res.send('Invalid id')
    }
  });

trainingRouter.route("/manage")
  .get((req, res, next) => {
    res.render('managing', { title: 'Manage Your Session' });

  })
  .post(async (req, res) => {
    const { idCheck, startingDate, endingDate } = req.body;

    let trainingData = {
      $and: [
        { userID: idCheck.trim() },
        { date: { $gte: startingDate, $lte: endingDate } }
      ]
    }
    bookings.find(trainingData)
      .then(records => {
        res.render("display-book", { "bookingInfo": records });
      })
      .catch(err => {
        console.error("Error fetching booking:", err);
        res.status(500).send("An error occurred while fetching booking.");
      });


  });

trainingRouter.route("/changingBegin")
  .post((req, res, next) => {
    console.log(`Current ID: ${req.body._id}`);
    res.render("edit", { _id: req.body._id })
  });


trainingRouter.route("/changing")
  .post((req, res, next) => {
    console.log(req.body);
    const { _id, newDate, newTime } = req.body;

    const trainingUpdate = {
      date: newDate.trim(),
      time: newTime.trim(),
    };

    bookings.findByIdAndUpdate(_id, trainingUpdate)
      .then((trainingUpdated) => {
        console.log("Training updated successfully.");
        res.render('managing', { title: 'Manage Your Session' });
      })
      .catch(err => {
        console.error("Error:", err);
        res.status(500).json({ error: "An error occurred while editing training." });
        next(err);
      });
  })

trainingRouter.route("/delete").post((req, res, next) => {
  bookings.findByIdAndDelete(req.body._id)
    .then((bookingDeleted) => {
      console.log("Booking deleted!!");
      res.render('managing', { title: 'Manage Your Session' });

    })
    .catch(err => {
      console.error("Error detected", err);
      res.status(500).json({ error: "An error occurred during deletion" })
    }
    )
}
);

trainingRouter.route("/contact").get((req, res, next) => {
  res.render("contact.ejs", { title: "Contact Us" });
  console.log("Routed to contact us page");
});

trainingRouter.route("/help").get((req, res, next) => {
  res.render("help.ejs", { title: "Help/Faqs" });
  console.log("Routed to help page");
});



module.exports = trainingRouter;
