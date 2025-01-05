// Main imports

const express = require("express"); // Acessing express methods
const trainingRouter = express.Router(); // Accessing router methods
const users = require('../models/userSchema') // Accessing various schemas
const bookings = require('../models/bookSchema')
const encryption = require('bcrypt') // Encrpytion for passwords and security 


// Beginning of router which handles all booking operations
trainingRouter
  .route("/") // Route to main page
trainingRouter.route("/book").get((req, res, next) => {
  res.render("book.ejs", { title: "Book a session" }); // Rendering the booking page with a title
  console.log("Routed to booking page");
})
  .post(async (req, res) => {
    // Identifying user from their chosen id
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


    // Date verification

    let todaysDate = new Date().toISOString().split('T')[0];
    const maxDate = new Date('2099-12-31');
    let minDate = new Date(todaysDate);

    function checkIfWithinDate(min, max, given) {
      let givenDate = new Date(given);
      let minDate = new Date(min);
      let maxDate = new Date(max);
      return givenDate > minDate && givenDate <= maxDate;
    }

    // Date checks

    let dateCheck = checkIfWithinDate(minDate, maxDate, bookingData.date);

    if (userData != []) {
      if (!dateCheck) {
        console.log(dateCheck);
        res.send('Invalid date')
      } else {
        const bookingConfirmation = await bookings.insertMany(bookingData)
      }
    } else {
      res.send('Invalid id')
    }
  });

trainingRouter.route("/manage")
  .get((req, res, next) => {
    res.render('managing', { title: 'Manage Your Session' });

  })
  .post(async (req, res) => {
    const { idCheck, startingDate, endingDate } = req.body; // Parsing data from body and defining it below

    let trainingData = {
      $and: [ // Training data packaged through
        { userID: idCheck.trim() },
        { date: { $gte: startingDate, $lte: endingDate } } // Date must be greater than starting date and less than ending date
      ]
    }
    bookings.find(trainingData) // Find data within training data and if it appears render display book 
      .then(records => {
        res.render("display-book", { "bookingInfo": records });
      })
      .catch(err => { // If not provide error 
        console.error("Error while finding booking, please try again", err);
        res.status(500).send("An error occurred while fetching booking.");
      });


  });

trainingRouter.route("/changingBegin")
  .post((req, res, next) => {
    console.log(`Current ID: ${req.body._id}`); // Log current id
    res.render("edit", { _id: req.body._id }) // Render the edits page 
  });


trainingRouter.route("/changing")
  .post((req, res, next) => {
    console.log(req.body);
    const { _id, newDate, newTime } = req.body; // Fetch all the edit data from the page 

    const trainingUpdate = {
      date: newDate.trim(),
      time: newTime.trim(), // Trim whitespace around the date and time
    };

    bookings.findByIdAndUpdate(_id, trainingUpdate) // Find through actual monogdb ids 
      .then((trainingUpdated) => {
        console.log("Training updated successfully.");
        res.render('managing', { title: 'Manage Your Session' });
      })
      .catch(err => {
        console.error("Error:", err);
        res.status(500).json({ error: "An error occurred while editing training, please try again" });
        next(err);
      });
  })

trainingRouter.route("/delete").post((req, res, next) => {
  bookings.findByIdAndDelete(req.body._id) // Use id from mongodb and then delete data entry
    .then((bookingDeleted) => {
      console.log("Booking deleted!!");
      res.render('managing', { title: 'Manage Your Session' }); // Render original managing page

    })
    .catch(err => {
      console.error("Error detected", err);
      res.status(500).json({ error: "An error occurred during deletion" })
    }
    )
}
);

// Routes to help and contact pages

trainingRouter.route("/contact").get((req, res, next) => {
  res.render("contact.ejs", { title: "Contact Us" });
  console.log("Routed to contact us page");
});

trainingRouter.route("/help").get((req, res, next) => {
  res.render("help.ejs", { title: "Help/Faqs" });
  console.log("Routed to help page");
});


module.exports = trainingRouter;