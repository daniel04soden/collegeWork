const express = require("express");
const router = express.Router();
const bcrypt = require('bcrypt')
const users = require("../models/userSchema")

/* GET users listing. */
router.get("/", function (req, res, next) {
  res.send("respond with a resource");
});

router.get("/login", function (req, res, next) {
  res.render("login.ejs", { title: "Login to Account" });
  console.log("Connected user");
});

router.get("/register", function (req, res, next) {
  res.render("register.ejs", { title: "Register for an account" });
  console.log("Made user");
});
// Registering

router.post("/register",async (req,res) => {
  const individualUser = {
    name: req.body.username,
    email: req.body.email,
    age: req.body.age,
    password: req.body.password,
    id: req.body.userid
  }

  const userExists = await users.findOne({name: individualUser.name});
  console.log(individualUser)

  if (userExists){
    res.send('Username taken, please choose a different username')
  }else{

    if (req.body.age < 18) {
      res.send('You are too young to sign up for this system please come back when you are 18')
    } else {
      
    const passSalt = 10;
    const saltHashPassword = await bcrypt.hash(individualUser.password, passSalt);

    const userData = await users.insertMany(individualUser);
    console.log(userData);
  }
  }


});

// Logging in 

router.post("/login", async (req,res) => {
  try {
    // Check for inputted username
    const checkName = await users.findOne({
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



module.exports = router;
