const express = require("express");
const router = express.Router();
const bcrypt = require('bcrypt')
const users = require("../models/userSchema")


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
  const passSalt = 10;
  const individualUser = {
    name: req.body.username,
    email: req.body.email,
    age: req.body.age,
    password: await bcrypt.hash(req.body.password,10),
    id: req.body.userid
  }

  const nameTaken = await users.findOne({name: individualUser.name});
  console.log(individualUser)

  if (nameTaken){
    res.send('Username taken, please choose a different username')
  }else{

    if (individualUser.age < 18) {
      res.send('You are too young to sign up for this system please come back when you are 18')
    } else {
      

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
       res.redirect('/') 
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
