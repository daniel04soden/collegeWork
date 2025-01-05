const fs = require('fs'); //this includes fs module, which is used for file management
const path = require('path'); // this includes path module. It deals with file and directory path

const http = require('http');

const hostname = 'localhost';
const port = 3000;



let server = http.createServer((req, res) => {
  console.log('Request for ' + req.url + ' by method ' + req.method);

  if (req.method == 'GET') { //GET is an HTTP method to request data from a specified resource
    var fileUrl;
    if (req.url == '/') 
        fileUrl = '/index.html'; // retrieve this file at the home page
    else 
        fileUrl = req.url;

    var filePath = path.resolve('./public'+fileUrl); // construct an absolute path from path segments e.g., 
    const fileExt = path.extname(filePath);
    if (fileExt == '.html') {
        fs.stat(filePath, function(err, stat) {
        console.log(fileExt)
        if(err == null) {
            console.log('File exists');
            res.statusCode = 200;
            res.setHeader('Content-Type', 'text/html');
            fs.createReadStream(filePath).pipe(res);
        } else if(err.code === 'ENOENT') {
            res.setHeader('Content-Type', 'text/html');
            res.end('<html><body><h1>Error 404: ' + fileUrl + 
                      ' not found</h1></body></html>');
          return;
        } else {
            console.log('Some other error: ', err.code);
        }
    }); 
    }
    else {
      res.statusCode = 404;
      res.setHeader('Content-Type', 'text/html');
      res.end('<html><body><h1>Error 404: ' + fileUrl + 
              ' not a HTML file</h1></body></html>');
    }
  }

});

server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
  });