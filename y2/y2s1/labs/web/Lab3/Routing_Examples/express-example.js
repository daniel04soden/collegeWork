const express = require('express'); //import express module. please note you need to install it first using nmp install express command
const app = express();  //app is the instance of express application

// Define a route for GET requests at the root ('/') path
app.get('/', (req, res) => {
  res.send('Hello, World!'); // this is the response of server when someone access localhost:3000
});

// Define a route for GET requests at the '/users' path
app.get('/users', (req, res) => {
  res.send('Users page'); //this is the response when someone access localhost:3000/users
});

// Define a route for POST requests at the '/users' path
app.post('/users', (req, res) => {
  res.send('Create a new user');
});

// Start the server
app.listen(3000, () => {
  console.log('Server is running on port 3000');
});