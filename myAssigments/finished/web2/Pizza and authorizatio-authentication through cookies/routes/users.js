var express = require('express');
var router = express.Router();

const bodyParser = require('body-parser');
var User = require('../models/users');

var authuser = require('../authorization');

router.use(bodyParser.json());

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});


router.get('/signup', (req, res, next) => {
  res.render('signup.ejs', { title: 'Sign Up' });   
})

router.post('/signup', (req, res, next) => {

  console.log(req.body);
  User.findOne({username: req.body.username})
  .then((user) => {
    if(user != null) {
      var err = new Error('User ' + req.body.username + ' already exists!');
      err.status = 403;
      next(err);
    }
    else {
      return User.create({
        username: req.body.username,
        password: req.body.password});
    }
  })
  .then((user) => {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'application/json');
    res.json({status: 'Registration Successful!', user: user});
  }, (err) => next(err))
  .catch((err) => next(err));
});

router.get('/login', (req, res, next) => {
  
  console.log('Start of authentication');
  if (req.session.user ==='authenticated'){
    console.log('I am authenticated');
    next()
  }
  else
  if ((!req.session.user) || ('loggedOut' === req.session.user)) {
      console.log('I am starting checking'+req.headers.authorization);
      console.log('Into login' +req.session.user);
      if(!req.session.user) {
        console.log('I am starting checking'+req.headers.authorization);
        var authHeader = req.headers.authorization;
        
        if ((!authHeader)|| ('loggedOut' === req.session.user)) {
          var err = new Error('You are not authenticated!');
          res.setHeader('WWW-Authenticate', 'Basic');
          err.status = 401;
          return next(err);
        }

        var auth = new Buffer.from(authHeader.split(' ')[1], 'base64').toString().split(':');
        var username = auth[0];
        var password = auth[1];
        delete req.headers.authorization;

        User.findOne({username: username})
        .then((user) => {
          if (user === null) {
            var err = new Error('User ' + username + ' does not exist!');
            err.status = 403;
            return next(err);
          }
          else if (user.password !== password) {
            var err = new Error('Your password is incorrect!');
            err.status = 403;
            return next(err);
          }
          else if (user.username === username && user.password === password) {
            req.session.user = 'authenticated';
            res.statusCode = 200;
            res.setHeader('Content-Type', 'text/plain');
            res.end('You are authenticated!');
          }
        })
        .catch((err) => next(err));
      }
    }
}); 



router.get('/logout', (req, res) => {
  
  if (req.session.user) {
      // delete session object
      req.session.destroy(function(err) {
        if(err) {
          return next(err);
        } else {
          return res.redirect('/');
        }
      });
  }

});

module.exports = router;
