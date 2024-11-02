// Node modules required for this app


const createError = require("http-errors");
const express = require("express");
const path = require("path");
const cookieParser = require("cookie-parser");
const logger = require("morgan");
const bcrypt = require('bcrypt');

const indexRouter = require("./routes/index");
const usersRouter = require("./routes/users");
const trainingRouter = require("./routes/trainingRouter");
const mongoose = require("mongoose")
const collection = require("./mongoose");

const app = express();

// Setting up EJS
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

// Using various routers to navigate the website

app.use("/", indexRouter);
app.use("/users", usersRouter);
app.use("/session", trainingRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get("env") === "development" ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render("error");
});

// Mongoose interaction code
mongoose.connect('mongodb://localhost:27017/training')
  .then(() => {
    console.log('Connected to database');
  })
  .catch((err) => {
    console.error('Error connecting to database:', err);
  });

//Important variables
let errorMessage;
let isLoggedIn = false; // Will be implemented, will determine whether a user will see login, logout or register


// Booking Code

app.post('/session/book', async (req,res) => {
  try {

    const checkID = await collection.User.findOne({userID: req.body.id})

    if (checkID === true) {
      res.render('index');
    } else {
      errorMessage = 'ID not found';
      res.send(errorMessage);
    }

  } catch {
    errorMessage = 'Unknown userID'
    res.send(errorMessage)}
  });

  module.exports = app;
