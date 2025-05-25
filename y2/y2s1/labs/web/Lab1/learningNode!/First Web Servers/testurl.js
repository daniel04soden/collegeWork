var http = require("http")
var dt = require('./datetime.js')


http.createServer((req,res)=>{
    var path = req.url.replace(/\/?(?:\?.*)?$/,'').toLowerCase()
    switch (path){
        case '':
            res.writeHead(200, {'Content-Type': 'text/html'});
            res.write("Homepage " + path);
            res.end();
            break;
        case '/about':
            res.writeHead(200, {'Content-Type': 'text/html'});
            res.write("About Page " + path);
            res.end();
            break;
        default:
            res.writeHead(200, {'Content-Type': 'text/html'});
            res.write("Page not found " + path);
            res.end();
            break;
    }
    

}).listen(3000)
