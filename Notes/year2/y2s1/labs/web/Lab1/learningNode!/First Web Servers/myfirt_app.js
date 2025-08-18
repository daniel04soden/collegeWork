const http = require('http'); //this module provides several key functions for different tasks of http server

const hostname = '127.0.0.1'; //localhost is a hostname that refers to the computer that is executing a program
const port = 3000; //port number

const server = http.createServer((req, res) => { //function from http module that creates an http server
 // d=new Date();
  res.statusCode = 200; //status means everything is fine
  res.setHeader('Content-Type', 'text/plain'); //type of content to be provided in the response to the client request
  res.end('Hello World');
  
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});