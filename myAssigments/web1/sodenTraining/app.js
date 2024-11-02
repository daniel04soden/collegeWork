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


//Important variables
let errorMessage;
let isLoggedIn = false; // Will be implemented, will determine whether a user will see login, logout or register

// Registering

app.post("/register",async (req,res) => {
  const individualUser = {
    name: req.body.username,
    password: req.body.password,
    email: req.body.email,
    age: req.body.age,
    id: req.body.userID
  }

  const userExists = await collection.User.findOne({name: individualUser.name});

  if (userExists){
    res.send('Username taken, please choose a different username')
  }else{

    if (age < 18) {
      res.send('You are too young to sign up for this system please come back when you are 18')
    } else {
      
    const passSalt = 10;
    const saltHashPassword = await bcrypt.hash(individualUser.password, passSalt);

    const userData = await collection.User.insertMany(individualUser);
    console.log(userData);
  }
  }


});

// Logging in 

app.post("/login", async (req,res) => {
  try {
    // Check for inputted username
    const checkName = await collection.User.findOne({
      name: req.body.username
    })
    if(!checkName){
      errorMessage = 'Unknown name'
      res.send(errorMessage)
    }

    // Compare the provided password to the matching password of that name
      const passwordsMatch = await bcrypt.compare(req.body.password, checkName.password);

      if (passwordsMatch) {
       res.render('book') 
      } else {
       errorMessage = 'Wrong password'
       res.send(errorMessage)
      }
    
  } catch{
    errorMessage = 'Username or password wrong';
   res.send(errorMessage);
   console.log(errorMessage);
  }
})

// Booking Code

app.post('/book', async (req,res) =>{
  try {

    const checkID = await collection.User.findOne({userID: req.body.id})

	if(checkID === true){
		res.render('index');
	}else{
	errorMessage = 'ID not found';
	res.send(errorMessage);
	}
    
  } catch{
    errorMessage = 'Unknown userID'
    res.send(errorMessage)
  }
}


module.exports = app;
