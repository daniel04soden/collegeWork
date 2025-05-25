# Three Implementations Of Event Handlers

1. Anonymous event handler
```java

new EventHandler<ActionEvent>(){
public void handle(ActionEvent event){
	Sysout.println("handle event using anon inner classes")
}

}
```

- Or using lambda 
```java
e -> System.out.println("Lamba is awesome")

```

2. Same class implements an interface

```java

public voind handle(ActionEvent E){
String commmand = e.getActionCommand();
if (command.equals("Open")){


openFile();
}else if( commmand.equals("quit"))

}

```

3. Separate Listener Classes

- Listener classes are Controllers where view is only for the interface and handling code is about application logic therefore controller

## Which is best?

- It depends
- For simple events, 2-3 line anon inner classes is best
- If one handle can deal with all interactions we use the second method
- But when we need more programming and details, we use Separate listener classes

- Moving to mvc design pattern we can start to separate out the functionality
- View defines the GUI
