## Web applications

- Client layer: Web browsers making http requests to the server.
- Server layer: This layer consists of a web server intercepting requests and passing them the response.
- Business Layer: Application server which is used by a web server to do dynamic tasks. This layer interacts with the data layer via a database or some external programs.
- Data: This consists of databases or any other source of data.

### Key elements

- Web servers are used to store, process, deliver websites and manage http requests and responses to the client system.
-  Static sites return the same hardcoded website whereas dynamic sites are generated programatically ie that as the user traverses the website, the output of their website may be different to another users output.

## Client side web dev

- Front end web dev which involves programs that run on the client/user's device 
- It focuses on creating the part of the website a user can interact with

#### Some tasks include

- Creating website layout
- Designing UI
- Adding visual elements like buttons, colours and fonts.
- Some common client side languages include HTML,CSS and JS

## Server side web dev

- Known as back-end web dev and invloves programs running on a server. This allows web browsers or clients to interact with a web server
- Some key tasks include:
	- Coding dynamic websites
	- Connecting websites to databases
- Common languages include:
	- PHP - Itself
	- JS - Node, Dino, Bun
	- Python - Django

## Nodejs

- Nodejs is a js web server environment that is cross platform.
- It is written in C++ and is a very fast js interpreter.
- Easily install libraries with the node package manager.
- It is an event driven architecture -> programming model where programs respond to external events
- Events -> could be any action such as http requests, file uploads, timers etc
- Events are associated with callback functions, functions which are executed asynchronously whenever an event occurs
- The event loop goes as follows:
1. The event is added to the queue whenever a new event is registered
2. If a callback function takes time to complete it won't block the whole program, instead other events can be processed in the mean time .
3. The event loop picks up these events from the queue and executes thier corresponding callbacks indvidually

- Async programming allows the server to respond to toher events while a task is running
- A big difference between node and more traditional web server environments is that node is single-threaded so it does not need to mange threads 
- Modules are the blocks of encapsulated code.
- Modules can be a single file or collection of multiple files/folders.
- Re-usability as well as the ability to break down a complex piece of code into manageable chunks.
- Three main types of modules:
	- Core modules
	- Local Modules
	- Third-party modules 
- Core modules are apart of the platform and come out of the box with a nodejs install.
- You must first import core modules to use in your application:
	 ```js
	 const mongoose = require('mongoose')
```

- These modules cna be loaded into the program using the above require function.
- Examples of core modules include fs, url, path etc
### HTTP module 
- HTTP is a core networking module which allows nodejs to transfer data over the HTTP.
- This module can create a http server that listens to server ports and gives a response back to the client.
- How to use:
```js 
const http = require('http')

const server = https.createServer(function(req,res){...});
```

How to start the server:
```js 
server.listen(port,...); // Listens on port number ...
```

### Path Module

- Core module that deals with file and directory paths:
- Incoming request message information is available through the first parameter req and the response message is constructed on the second parameter "res".
### How to use:

```js
const path = require('path');
```


### Some key functions 

```js
path.resolve('./public+fileUrl');
path.extname(filePath);
```


## Fs Module


### Writing to a file
- Core module that deals with file management 
```js
fs.writeFile(file,data,options,callbackFunction) // Method for writing
```

- File where data is to be written
- What data is the be written
- Options for writing that file 
- Callback function when the method is executed -> one parameter (error) -> thrown when the operation fails

### Reading data from a file 

```js
fs.readFile(filename,encoding, callback function)
```

- Filename to read  from
- Encoding (default utf8)
- Callback function after reading the file, (data,error) -> content of the file and error thrown when the operation fails.

## Local modules

- Local modules are created locally in your nodejs application, similar to java classes or importing in python
- Another file is able to be used by importing it with the same require functionality

## Third party Modules

- Third party modules are modules available online using the Node Package manager
- these modules can be installed in the project folder or globally
- npm install __

## Node as a web server

- Web servers listen for requests, parse requests and also send responses.
- Node will get requests and turn them from raw bytes into JS objects that you can handle
- One object for the request and one for the response (why isnt res just resp that makes so much more sense omg)
- These objects will then be sent to a js function that you'll write. You'll use the object req to see what the user wants and use res to prepare your response.

### Creating the web server
- To create a server that listens to ports and gives a response goes as follow:
- Node give a http server and client interfaces through the http module:
```js
const http = require('http');
```

- To create such a server use the http.createServer();. This accepts one arugrment, a callbacjk function that will be called on each HTTP request received by the server
- This request callback receives, as arguments the request and response objects.
- When we finish writing such response we call res.end which then signals to node that the response is all done and ready to be sent to the browser