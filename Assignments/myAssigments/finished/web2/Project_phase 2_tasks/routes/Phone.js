const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
var objectId = require('mongodb').ObjectId;
const phones = require('../models/phone');
const mongo = require('mongodb');

const phoneRouter = express.Router();

//all of the endpoints / URI's

phoneRouter.get('/help', function(req, res, next) {
    res.render('help', { title: 'help' });
});
phoneRouter.get('/', function(req, res, next) {
    res.render('home', { title: 'home' })
});
phoneRouter.get('/table', function(req, res, next) {
    res.render('table', { title: 'table' })
})

phoneRouter.route('/')
    .get((req, res, next) => {
        res.end("just checking --> nothing to do")
    })

phoneRouter.route('/create')
    .get((req, res, next) => {
        res.render('newphone.ejs', { title: 'phone usage' });
    })


//Creating a new entry, sending to mongo, finding the data, rendering table page
.post((req, res, next) => {
    phones.create(req.body)
        .then((phonecreated) => {
            phones.find()
                .then((phonesfound) => {
                    res.render('table.ejs', { 'phonelist': phonesfound, title: 'All phones' });
                }, (err) => next(err))
                .catch((err) => next(err));
        }, (err) => next(err))
        .catch((err) => next(err));
})


// error pages...
.put((req, res, next) => {
        res.send('PUT operation not supported on /phones/create');
    })
    // error pages
    .delete((req, res, next) => {
        res.end('Delete operation not  supported on /phones/create');

    });
//deleting user by id
phoneRouter.route('/delete')
    .post((req, res, next) => {
        phones.findByIdAndDelete(req.body.id).then(reportsfound => {
                res.render("success.ejs", { title: "table page" });

            }, (err) => next(err))
            .catch((err) => next(err));
    });
//finding the individual user's date by name
phoneRouter.route('/find')
    .post((req, res, next) => {
        phones.find({ name: req.body.name })
            .then((phonetaken) => {
                phones.find(req.body)
                    .then((phonefound) => {
                        res.render('oneFound.ejs', { 'phonelist': phonefound, title: 'All data recieved from user: ' + req.body.name }); //can use datadisplay
                    }, (err) => next(err));
            })
    })

//takes the id of the user you want to update/edit, puts details on page
phoneRouter.route('/update')
    .post((req, res, next) => {
        phones.findById({ _id: req.body.id })
            .then((phonesfound) => {
                console.log(phonesfound);
                res.render("updatePage.ejs", { "phonelist": phonesfound, title: "Editing phones" });
            }, (err) => next(err))
            .catch((err) => next(err));
    }, (err) => next(err));

//once you submit the new form with updated data, it updates the mongodb, renders a "update successful" page
phoneRouter.route('/updateComplete')
    .post((req, res, next) => {
        phones.findByIdAndUpdate(req.body.id, req.body)
            .then(phones.find()
                .then((phonesfound) => {
                    res.render("success2.ejs", { "phonelist": phonesfound, title: "Editing reports" });
                }, (err) => next(err))
                .catch((err) => next(err)));
    })

module.exports = phoneRouter; //exports from this file so that other files are allowed to access the exported code