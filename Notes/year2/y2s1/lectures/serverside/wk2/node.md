# What is Node js:

- **Node.js** is a cross-platform, open-source JavaScript runtime
  environment that can run on Windows, Linux, Unix, macOS, and more

- Node is single threaded so we dont need o manage threads

  - It uses asnychronous programming

## Node Modules

- **Modules**: Blocks of encapsulated code. they can be single file or a
  collection of many files/folders. Reusability as well as the ability
  to break down a piece of code into manageable chunks.

  Types of Modules:

  - Core
  - Local
  - Third-part modules

### Main Core modules

- Apart of the platform and come with the Node.js install

- Http module would be a core module and only requires the follow:

- Other modules: fs (files), url, path

  ``` {.dot js="test.js"}
  var http = require('http');
      // Generally:
  var module = require('module_name');
  ```

1.  HTTP Module

    - It is the core networking module and allows nodejs to transfer
      data over http

    - It can create a http server that lsitens to server ports and gives
      a reponse back to the client

      ``` {.dot javascript="js"}

      const server = https.createServer(function(req, res){...})

      ```

      - **Call back functions** -\> executed when the request arrives,
        req and res are javascript objects

    - How to start the server?

      - server.listen(port,...) -\> listens on given port number

2.  Path Module

    - Core modulle that deals with file and directory path

    - Path.resolve() - constructs an absolute path from path segments

    - Path.extname(filePath)

3.  FS module

    - Deals with file management systems

    - Writes data to a file

    - Reads data from a file

      - f.writeFile(file,data,options,callback function) method One
        paramaeter for callback
      - f.readFile(filename,encoding,callback function) method Two
        paramaeters for callback

### Third-Party modules

- examples: mongoose, express, etc
- Modules that are available online using the NPM

## Node.js as a web server

- Creating a web server that lsitens to server ports and gives back a
  reponse to the client
- Res.end signals node that the response is all done and ready to be
  sent to the client
- When a request arrives, the anon callback function is called
  (func(request,response))
