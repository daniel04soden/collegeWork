var express = require('express');
var router = express.Router();
const phones = require('../models/phone');
//all of the endpoints / URI's
router.get('/', function(req, res, next) {
    res.render('home', { title: 'home' });
});
router.get('/help', function(req, res, next) {
    res.send('This is Help Page');
});
router.get('/table', function(req, res) {
    phones.find().then((phonesfound) => {
        res.render('table', { 'phonelist': phonesfound, title: 'tablepage' });
    })
});
router.get('/about', function(req, res, next) {
    res.send('This is about Page');
});


module.exports = router; //exports from this file so that other files are allowed to access the exported code