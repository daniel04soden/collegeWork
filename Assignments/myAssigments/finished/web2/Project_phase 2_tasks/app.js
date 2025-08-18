var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var mongoose = require('mongoose');

var indexRouter = require('./routes/index');
var phoneRouter = require('./routes/Phone');


var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');


app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

//routing to index and to phone, used in form and database
app.use('/', indexRouter);
app.use('/phone', phoneRouter);

//creating the databas/connecting to existing database
const phoneDb = 'mongodb://127.0.0.1/ServerSideWebDevProject';
const connect = mongoose.connect(phoneDb);

//logging in console if connected to database or not
connect.then((db) => {
    console.log("Connected correctly to server :)");
}, (err) => { console.log("this aint gonna work chief", err); });

// catch 404 error, forward to error handler
app.use(function(req, res, next) {
    next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

module.exports = app;