# What are design Patterns?

- From a high level design patterns are a solution to a problem in context

- It also has two other definitions depending on this context

- A solution to a problem in context typically within shared address space
- A solution to a problem in a context where often across multiple addresses, spaces, processes or services


## Structure 

- Pattern Name: A conceptual handle and category for the pattern
- Intent: What problem does the pattern address?
- Motivation: A scenario that illustrates the problem
- Applicability: In what situation can these patterns be used?
- Structure: Diagram using the object modelling technique
- Participants: Classes and objects in design.
- Collaborations: How classes and objects in the design collaborate
- Consequences: What objectives does the pattern achieve?

- Implementation: Implementation details to consider, language-specific issues 

- Sample code: Sample code in smalltalk, Java or C++
- Known uses: Examples from the real world
- Related patterns: Comparison and discussion of related


## Anti Patterns

- Commonly implemented solutions that are either ineffective or will lead to later problems (eg with maintenance, debugging etc)


## Categories of Patterns

1. Creational
2. Behavioural
3. Structural
4. Concurrent (Less common)


### Creational
- Creational patterns describe the way objects are created and are used when a decision must be made at the time a class/struct is instantiated. 
#### Examples:
##### Singleton 
- Encapsulates the creation of an object in order to maintain control over it through only creating a single instance of it.
```go
// single.go
package main

import (
	"fmt"
	"sync"
)


type singleton struct{
value string
}

var instance *singleton
var once sync.Once

func getInstance() *singleton{
	once.Do(func(){
		instance = &singleton{value:"I am the only instance"}	
	})
return instance
}
```

- sync.Once is a synchronisation primitive provided by Go's sync package which ensures a piece of code is executed only once, regardless of how many Go-Routines are trying to execute it
##### Factory method
- Delay creation until run time (not compile time) which one of several compatible classes is used to instantiate an object.
###### Code snippet:

```go
package main

import "fmt"

type Shape interface {
	Draw()
}

type Square struct{}

func (s Square) Draw() {
	fmt.Println("Drawing a square")
}

type Circle struct{}

func (c Circle) Draw() {
	fmt.Println("Drawing a circle")
}

func ShapeFactory(kind string) (s Shape) {
	switch kind {
	case "circle":
		return Circle{}
	case "square":
		return Square{}
	default:
		return nil
	}
}

func main() {
	s1 := ShapeFactory("square")
	s2 := ShapeFactory("circle")
	s1.Draw()
	s2.Draw()
}
```

- General idea: Have abstract object which can take many forms, create as interface, create methods for specifics of interface, create structs of interface, create match case factory method

##### Abstract Factory pattern
```go
package main

import (
	"fmt"
	"os"
)

type Logger interface {
	Log(s string)
}

type FileLogger struct{}

func (f FileLogger) Log(s string) {
	file, err := os.Create("log.txt")
	if err != nil {
		fmt.Println("Error creating file:", err)
		return
	}
	defer file.Close()
	log := "Log: " + s
	_, err = file.WriteString(log)
	if err != nil {
		fmt.Println("Error writing to file:", err)
		return
	}
}

type TextLogger struct{}

func (t TextLogger) Log(s string) {
	fmt.Println("Log: ", s)
}

type LoggerFactory interface {
	CreateLogger() Logger
}


type FileLoggerFactory struct{}

func (f FileLoggerFactory) CreateLogger() Logger {
	return FileLogger{}
}

type TextLoggerFactory struct{}

func (t TextLoggerFactory) CreateLogger() Logger {
	return TextLogger{}
}

func GetFactory(kind string) LoggerFactory {
	switch kind {
	case "file":
		return FileLoggerFactory{}
	case "text":
		return TextLoggerFactory{}
	default:
		return nil
	}
}

func main() {
	fileFactory := GetFactory("file")
	textFactory := GetFactory("text")

	if fileFactory == nil || textFactory == nil {
		fmt.Println("Invalid factory kind specified.")
		return
	}

	l1 := fileFactory.CreateLogger()
	l2 := textFactory.CreateLogger()

	l1.Log("Hi there, this is file logging.")
	l2.Log("Hi there, this is text logging.")
}
```
### Structural
- Prescribes the organisation of classes and objects
- Patterns concerned with how classes inherit from each other or how they are composed from other classes.
- Common structural patterns include:
	- Adapter
	- Proxy
	- Decorator
- These are similar in that they introduce a level of indirection between a client class and a class it wants to use. Their intents are different however.

#### Adapter
- Uses indirection to modify the interface of a class to make it easier for a client class to use it
- Used to allow the reuse of a class that is similar but not the same as the class the client wants to see.
- Typically the original class is capable of supporting the behaviour but does not have the interface the client class expects and it is not possible or practical to alter the original class
- Perhaps the source code isn't available or it is used elsewhere and changing the interface is innapropriate

##### Code sample - USBs Lightning port
```go
package main

import "fmt"

type Client struct{}

func (c *Client) InsertLightningConnectorIntoComputer(com Computer) {
	fmt.Println("Client inserts lightning into computer")
	com.InsertIntoLightningPort()
}
package main

type Computer interface {
	InsertIntoLightningPort()
}
package main

import "fmt"

type Mac struct{}

func (m *Mac) InsertIntoLightningPort() {
	fmt.Println("Lightning connector is plugged into mac machine")
}
package main

import "fmt"

type Windows struct{}

func (w *Windows) insertIntoUsbPort() {
	fmt.Println("USB connector is plugged into windows machine.")
}
package main

import "fmt"

type WindowsAdapter struct {
	windowsMachine *Windows
}

func (w *WindowsAdapter) InsertIntoLightningPort() {
	fmt.Println("Adapter converts lightning into usb")
	w.windowsMachine.insertIntoUsbPort()
}
package main

func main() {
	client := &Client{}
	mac := &Mac{}

	client.InsertLightningConnectorIntoComputer(mac)

	windowsMachine := &Windows{}
	windowsMachineAdapter := &WindowsAdapter{
		windowsMachine: windowsMachine,
	}

	client.InsertLightningConnectorIntoComputer(windowsMachineAdapter)
}
```
#### Decorator
- Uses indirection to add behaviour to a class without unduly affecting the client class
- It is a standin for another class and also has the same interface as that class usually because it is a subclass.
- The intent is to extend the functionality of the original class in a way that is transparent to the client class.
- In the example below and for my own implementation, to apply the decorator we use a factory as well
```go
package games

type Game interface {
	Name() string
	PlayOnce() string // returns outcome text (no I/O yet)
}

package decorate

import (
	"log"
)

type Game interface {
	Name() string
	PlayOnce() string
}

type Logging struct {
	Inner Game
}

func (d Logging) Name() string { return d.Inner.Name() }

func (d Logging) PlayOnce() string {
	out := d.Inner.PlayOnce()
	log.Printf("[LOG] %s → %s", d.Name(), out)
	return out
}

func WrapLogging(g Game) Game { return Logginpackage decorate

import "strings"

type Scoring struct {
	Inner  Game
	Points int
}

func (d *Scoring) Name() string { return d.Inner.Name() }

func (d *Scoring) PlayOnce() string {
	out := d.Inner.PlayOnce()
	if strings.Contains(out, "WIN") || strings.Contains(out, "+points") {
		d.Points += 1
	}
	return out
}

func WrapScoring(g Game) *Scoring { return &Scoring{Inner: g} }g{Inner: g} }

package games

import (
	"dice-arcade/internal/games/decorate"
	"dice-arcade/internal/games/legacy"
	"fmt"
)

func New(kind string) (Game, error) {
	switch kind {
	case "highlow":
		return HighLow{}, nil
	case "pig":
		return Pig{}, nil
	case "highlow_legacy":
		l := legacy.NewHighLowLegacy()
		return legacy.NewHighLowAdapter(l), nil
	case "highlow+log":
		g := HighLow{}
		return decorate.WrapLogging(g), nil
	case "pig+score":
		g := Pig{}
		return decorate.WrapScoring(g), nil
	case "highlow+log+score":
		g := HighLow{}
		return decorate.WrapLogging(decorate.WrapScoring(g)), nil
	default:
		return nil, fmt.Errorf("unknown game: %s", kind)
	}
}
```
#### Proxy
- Uses indirection to transparently provide a stand-in for another class. 
- **Proxy** is a structural design pattern that provides an object that acts as a substitute for a real service object used by a client. A proxy receives client requests, does some work (access control, caching, etc.) and then passes the request to a service object.
- In the code below we see a web server such as nginx act as a proxy for the original application server:
	- It provides controlled access to your application server
	- It can do rate limiting
	- It can do request caching
```go
package main

type server interface {
	handleRequest(string, string) (int, string)
}


package main

type Nginx struct {
	application       *Application
	maxAllowedRequest int
	rateLimiter       map[string]int
}

func newNginxServer() *Nginx {
	return &Nginx{
		application:       &Application{},
		maxAllowedRequest: 2,
		rateLimiter:       make(map[string]int),
	}
}

func (n *Nginx) handleRequest(url, method string) (int, string) {
	allowed := n.checkRateLimiting(url)
	if !allowed {
		return 403, "Not Allowed"
	}
	return n.application.handleRequest(url, method)
}

func (n *Nginx) checkRateLimiting(url string) bool {
	if n.rateLimiter[url] == 0 {
		n.rateLimiter[url] = 1
	}
	if n.rateLimiter[url] > n.maxAllowedRequest {
		return false
	}
	n.rateLimiter[url] = n.rateLimiter[url] + 1
	return true
}


package main

type Application struct {
}

func (a *Application) handleRequest(url, method string) (int, string) {
	if url == "/app/status" && method == "GET" {
		return 200, "Ok"
	}

	if url == "/create/user" && method == "POST" {
		return 201, "User Created"
	}
	return 404, "Not Ok"
}

package main

import "fmt"

func main() {

	nginxServer := newNginxServer()
	appStatusURL := "/app/status"
	createuserURL := "/create/user"

	httpCode, body := nginxServer.handleRequest(appStatusURL, "GET")
	fmt.Printf("\nUrl: %s\nHttpCode: %d\nBody: %s\n", appStatusURL, httpCode, body)

	httpCode, body = nginxServer.handleRequest(appStatusURL, "GET")
	fmt.Printf("\nUrl: %s\nHttpCode: %d\nBody: %s\n", appStatusURL, httpCode, body)

	httpCode, body = nginxServer.handleRequest(appStatusURL, "GET")
	fmt.Printf("\nUrl: %s\nHttpCode: %d\nBody: %s\n", appStatusURL, httpCode, body)

	httpCode, body = nginxServer.handleRequest(createuserURL, "POST")
	fmt.Printf("\nUrl: %s\nHttpCode: %d\nBody: %s\n", appStatusURL, httpCode, body)

	httpCode, body = nginxServer.handleRequest(createuserURL, "GET")
	fmt.Printf("\nUrl: %s\nHttpCode: %d\nBody: %s\n", appStatusURL, httpCode, body)
}
```
### Behavioural
- Behavioural patterns prescribe the way objects interact with each other
- They help make complex behaviour manageable by specifying the responsibilities of objects and the ways they communicate with each other
#### Template pattern

- Define a skeleton of algorithm
- Defer some steps to subclasses
- Redefine other steps in subclasses
- Often a result of refactoring common code
```java
public interface Calculator {  
public void calculate(double operand);  
public double getResult();  
}  
public abstract CalculatorTemplate implements Calculator() {  
private double result;  
private boolean initialized = false;  
public final void calculate(double operand) {  
if (this.initialized) {  
this.result = this.doCalculate(this.result, operand); 
}  
else {  
this.result = operand;  
this.initialized = true;  
}  
}  
public final double getResult() {  
return this.result;  
}  
protected abstract doCalculate(double o1, double o2);   
}

public class AdditionCalculator extends CalculatorTemplate {  
protected doCalculate(double o1, double o1) {  
return o1 + o2;  
}  
}  
public class SubtractionCalculator extends CalculatorTemplate {  
protected doCalculate(double o1, double o1) {  
return o1 - o2;  
}  
}  
AdditionCalculator ac = new AdditionCalculator();  
Double result = ac.calculate(5.5, 10.35);  
SubtrationCalculator sc = new SubtractionCalculator();  
Double result2 = sc.calculate(45.2, 20.0);
```
- Another simple example is how one can put all the functionality of a DAO (Data access object) into one class, however if we have multiple different classes it makes sense to make individual DAOs and provide a simple template for that
### Concurrent

- Prescribes the way access to shared resources is coordinated or sequenced.
- The most common concurrency pattern is the 'Single thread execution' where it must be ensured that only one thread has access to a section of code at a time

#### Single thread execution / Critical region/Section

 - Section of code that obtains a resource that must be shared like opening a port or a sequence of operations as follows:
1. Obtain a value
2. Perform calculations on it
3. Update the value
4. Store the value


- The singleton pattern has good examples of single thread execution, problem motivating this pattern first arises because this example uses lazy instantiation delaying instantiating until necessary thereby creating the possibility that two different threads may call getInstance at the same time
```java
public static synchronized Sequence getInstance(){
	if (instance == null){
		instance = new Sequence();
	}

	return instance
}

public static synchronized int getNext(){
	return ++counter;
}
```

## Strategy pattern
- Strategy is used to allow different implementations of an algorithm or operations to be selected dynamically at run time.
- Typically any common behaviour is implemented in an abstract class and concrete subclasses provide the behaviour that differs.
- The client is generally aware of the different strategies that are available and can choose between them

- Example:

```java
public interface Comparator {  
public int compare(Object o1, Object o2); 
}  
public class DateComparator implements Comparator {  
public int compare(Object o1, Object o2) {  
return ((Date) o1).compareTo((Date) o2);  
}  
}  
public class StringIntegerComparator implements Comparator {  
public int compare(Object o1, Object o2) {  
return Integer.parseInt((String) o1) – Integer.parseInt((String) o2);  
}  
}  
public class ReverseComparator implements Comparator {  
private final Comparator c;  
public ReverseComparator(Comparator c) {  
this.c = c;  
}  
public int compare(Object o1, Object o2) {  
return c.compare(o2, o1);  
}  
}

Arrays.sort(stringArray,  
new ReverseComparator(  
new StringIntegerComparator()  
));
```

## Template pattern

- Template pattern is to not allow behaviour to be implemented in different ways as in strategy but rather to ensure that certain behaviours **ARE** implemented.
- In other words where to focus of Strategy is to allow variety, template is to enforce consistency.
- The template pattern is implemented as an abstract class and is often used to provide a blueprint or an outline for concrete subclasses