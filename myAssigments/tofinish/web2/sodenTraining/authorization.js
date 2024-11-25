var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var mongoose = require('mongoose');
var session = require('express-session');
var FileStore = require('session-file-store')(session);
var User = require('./models/users.js');
var express = require('express');
var router = express.Router();
const bodyParser = require('body-parser');

function authorize(req,res,next) {
    console.log("Beginning authentication")

    if (req.session.user === "authenticated") {
        console.log("User is authenticated");
        next()
    }else if ((!req.session.user) || ('loggedOut' === req.session.user)){
        console.log("I am starting to check" + req.headers.authorization);
        var authHeader = req.headers.authorization;
        if ((!authHeader)|| ('loggedOut' === req.session.user)) {
            var err = new Error('You are not authenticated!');
            res.setHeader('WWW-Authenticate', 'Basic');
            err.status = 401;
            return next(err);
        }
     }

    let auth = new Buffer.from(authHeader.split(" ")[1],"base64").toString().split(":");

    let uName = auth[0];
    let pass = auth[1];

    delete req.headers.authorization;

    User.findOne({uName: username})
    .then
    }