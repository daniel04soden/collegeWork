// Node modules required for this app

const createError = require("http-errors");
const express = require("express");
const path = require("path");
const cookieParser = require("cookie-parser");
const logger = require("morgan");

const indexRouter = require("./routes/index");
const usersRouter = require("./routes/users");
const trainingRouter = require("./routes/trainingRouter");
const mongoose = require("mongoose")

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


function authorization (req,res,next) {
  console.log(req.headers);
  let authHeader = req.headers.authorization;

  if (!authHeader){
    var err = new Error("You are not authenticated");
    res.setHeader("WWW-Authenticate","Basic");
    err.status = 401;
    return next(err);
  }
}



  module.exports = app;
