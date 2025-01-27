# What is javafx?

- Open source client application platform for any OS that runs Java
- Main idea is to allow the developer to create rich hardware-accelerated and highly portable applications.
- It was intended to replace swing but that never happened

## Why not swing?

- Released in 1997 
- Swing is the default gui library
- Reached peak maturity years ago
- JavaFX has less components
- Javafx shines in internet applications especially from a design POV.

## Handling events in JavaFX

- JavaFX event handlers have functions similar to what's implemented in JS.
- Examples include Windows, Mouse, Drag, Key.
- A class that manages an event can implement an interface eventHandler

# Javafx Objects
- The total object graph of all the controls, layouts etc attatched to a scene is called the scene graph
## Structure of graph

- Each item in javafx is node as a node, they can have children, be the root node or be a single node without any children.
- Respectively these are known as Branch,roots and leaf nodes.
- A control object must be attached to some Scene object in order for it to be visiable to the user
- Typically we next control objects inside some layout object

## Controls

- Buttons - onclick, onsubmit etc
- Check boxes - three states 
- Label
- Text fields - Collects user input
- Password fields - Hidden as a text field
- Radio button - check boxes but only one selection
- Etc... 

## Layouts

- In JavaFX layouts provide structures to organise objects
- These contain other control and layout objects
- They are sometimes called parent components as they contain child components
- A layout must be attached to some scene graph to be visible
- Stack panes

``` java
button btn = new Button();
btn.setText("Say hello world");
Stackpane root = new Stackpane();
root.getChildren().add(btn);

Scene scene = new Scene(root,400,400);
primaryStagel.setScene(scene);
primary.setTitle("setTitle");
...
```

## Group 
- Container components that doesn't apply any special layout for the children
- Every child component or node will be kept the same at (0,0)

## HBOX and VBOX

- Hbox is a components which positions all children in a horizontal row
- VBox places them in a vertical row
- Made by javafx.scene.layout.Hbox
``` java
Hbox hbox = new Hbox(button1,button2) // Must be passed in order of being displayed
```

- Grid pane lays out the children within a  flexible grid of rows and columns if a border and or padding is set then its content will accommodate those insets
- It then may be styled using CSS backgrounds (?)
## Event handlers
- Event corresponding to user interactions with components
- Associated with different event types ie stages window event, buttons action event mouse and keyboards input event
- Objects are called listeners
### Listening for events

- Attach a listener to the component
- Listener's appropriate method will be claled when an event occurs
- For action events, use event handlers
#### Window events
- Close, hiding and show requests
- Eg; Are you sure you want to exit the program.

#### Action event
- Most common siomple event type 
- Represent an acction occuring on a GUI compoenents
- Created by:
	- Button clicks
	- menu clicks
	- etc
``` java

public string clear(){

return 0; // i think
}
btn3.setONaction()}{
public void handle(Action event){
	textField.setText(clear());
}

}
```


# Documentation analysis

- ![[Screenshot_20250127_090700.png]]
- Figure 1 contains the architecture around JavaFX applications.
- At the top sit he stage which represents the native OS window ie windows window, macOS, Linux etc.
- Below this we have the Scene which is a container for the JavaFX scene graph

- All elements within a scene graph are scene as Node objects.
- The three main types of nodes are root nodes, branch nodes and leaf nodes. 
- Root node is the only node that does not have a parent and this node is directly contained by a scene which can be seen in graph above.
- The primary difference between a  branch and a leaf is that a leaf node does not have children. IE Branch has children, leaf doesn't.

- In the below code i have made the following structure:
``` java
package com.example.javafx;  
  
import javafx.application.Application;  
import javafx.fxml.FXMLLoader;  
import javafx.scene.Group;  
import javafx.scene.Scene;  
import javafx.scene.image.Image;  
import javafx.scene.paint.Color;  
import javafx.stage.Stage;  
  
import java.io.IOException;  
  
public class HelloApplication extends Application {  
    @Override  
    public void start(Stage stage) throws IOException {  
  
        // Creating scene  
        Group root = new Group();  
  
        Scene scene = new Scene(root, Color.BLUE);  
        // Image icon = new Image("./tien.jpg");  
  
  
        stage.setTitle("KDE App woooooo");  
        // stage.getIcons().add(icon);  
  
        // Displaying stage        stage.setScene(scene);  
        stage.show();  
  
    }  
  
    public static void main(String[] args) {  
        launch(args);  
    }  
}
```

![[hello.png]]
## Transformations 
- The three most common transformations include 
	- Translating
	- Rotating
	- Scaling

### Translate

- In javafx and computer graphics, translate means move, so we can translate a box by  a certain amount of pixels in the x or y axis.
``` java
privat void transform(Rectangle box){
	box.setTranslateX(100);
	box.setTrabslateY(200);
}
```

### Scale

- You can also apply scaling to make a node larger or smaller. Scaling value is a ratio. By defualt a node has a scaling value of 1 in each axis. From there we can enlarge our box by applying scaling of 1.5 in X and Y axes.

```java
private void transform(Rectangle box){
	box.setScaleX(1.5);
	box.setScaleY(1.5);
}
```


### Rotate 