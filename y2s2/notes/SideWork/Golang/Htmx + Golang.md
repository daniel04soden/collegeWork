# Basics of go + htmx


## Setup file
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

# Sending data to the html file
- The starter idea for htmx is simply grabbing go templates from the cmd file, and then using a `{{.StructName}}` within the HTML file to grab the dynamic data

- Sample Struct structure for html data

## Sample struct

```go
func main(){
	person := struct{
	Person string	
	}{
		Person: "Daniel"	
	}
}
```

## Inline data

```html
<ul>
<li>
{{.Person}}
</li>
</ul>
```

## Enabling htmx

- For simple go to html interaction, include this:

```html
<script src="https://unpkg.com/htmx.org@2.0.4/dist/htmx.js" integrity="sha384-oeUn82QNXPuVkGCkcrInrS1twIxKhkZiFfr2TdiuObZ3n3yIeMiqcRzkIcguaof1" crossorigin="anonymous"></script>
```

- This is a simple cdn that allows the usage of dynamic data within html tags

# Prime learning

## Boilerplate for templates no routes
```go
package main

  

import (

"html/template"

"io"

  

"github.com/labstack/echo/v4"

"github.com/labstack/echo/v4/middleware"

)

  

type Templates struct {

templates *template.Template

}

  

func (t *Templates) Render(w io.Writer, name string, data interface{}, c echo.Context) error {

return t.templates.ExecuteTemplate(w, name, data)

}

  

func newTemplate() *Templates {

return &Templates{

templates: template.Must(template.ParseGlob("views/*.html")),

}

}

  

func main() {

e := echo.New()

e.Use(middleware.Logger())

  

e.Renderer = newTemplate()

}
```