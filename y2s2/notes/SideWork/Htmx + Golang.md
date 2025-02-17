

- File structure
```sh
go mod init "projectName"
```
- go.mod
- main.go
- cmd
	- other go code
- index.html - main home page
- templates
	- foo.html / htmx


- Basic Starter code Structure: main.go
```go

import (
"fmt"
"log"
"net/http"
"text/template"
)

func main(){
	var homePath = "./index.html"
	var port string = ":3000" // Can be 8000 or 8080 too
	fmt.Println("Server started on port", port)
	initPage := func(w http.ResponseWriter, r *http.Request){
	templ:= template.Must(template.ParseFiles(index.html))
	err := templ.Execute(w,nil)
	}
	http.HandleFunc("/",initPage)
	log.Fatal(http.ListenAndServe(port,nil))
}

```

- Basic code structure: index.html


``` html
<!doctype html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Document</title>  
</head>  
<body>  
	  <h1>Hello, World</h1>
</body>  
</html>
``` 
