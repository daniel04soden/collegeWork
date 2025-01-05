const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const pizzas = require('../models/pizza');

const pizzaRouter = express.Router();

pizzaRouter.use(bodyParser.json());

pizzaRouter.route('/')
.get((req,res,next) => {
})
.post((req, res, next) => {
})
.put((req, res, next) => {
})
.delete((req, res, next) => {
});


pizzaRouter.route('/create')
.get((req,res,next) => {
    res.render('newpizza.ejs', { title: 'Pizza shop' });   
})

.post((req, res, next) => {
    console.log (req.body);
    pizzas.create(req.body)
    .then((pizza) => {
        pizzas.find({customer:req.body.customer})
         .then((pizza) => {
                res.render('currentorder',{'pizzalist' : pizza, title:'Ordered Pizzas'} );
        }, (err) => next(err))
    .catch((err) => next(err));
    }, (err) => next(err))
    .catch((err) => next(err));
})

.put((req, res, next) => {
    res.statusCode = 403;
    res.end('PUT operation not supported on /pizzas/create');
})

.delete((req, res, next) => {
    res.statusCode = 403;
    res.end('Delete operation not  supported on /pizzas/creste');
    
});


pizzaRouter.route('/:customerId')
.get((req,res,next) => {
    pizzas.find({customer:req.params.customerId})
    .then((pizza) => {
        /*res.statusCode = 200;
        res.setHeader('Content-Type', 'application/json');
        res.json(pizza); */
        res.render('pizzalist',{'pizzalist' : pizza, title:'Order Pizza'} );
    }, (err) => next(err))
    .catch((err) => next(err));
})
.post((req, res, next) => {
    res.statusCode = 403;
    res.end('POST operation not supported on /pizzas/'+ req.params.dishId);
})
.put((req, res, next) => {
    pizzas.findByIdAndUpdate(req.params.dishId, {
        $set: req.body
    }, { new: true })
    .then((dish) => {
        res.statusCode = 200;
        res.setHeader('Content-Type', 'application/json');
        res.json(dish);
    }, (err) => next(err))
    .catch((err) => next(err));
})
.delete((req, res, next) => {
    pizzas.findByIdAndRemove(req.params.dishId)
    .then((resp) => {
        res.statusCode = 200;
        res.setHeader('Content-Type', 'application/json');
        res.json(resp);
    }, (err) => next(err))
    .catch((err) => next(err));
});

module.exports = pizzaRouter;