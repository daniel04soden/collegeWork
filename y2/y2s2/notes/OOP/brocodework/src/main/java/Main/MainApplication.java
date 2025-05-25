package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Group root = new Group();


        //Scene configuration
        Scene scene = new Scene(root,600,600,Color.RED);


        // Provided text

        Text text = new Text("Hello there!");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdan",50));
        text.setFill(Color.LIMEGREEN);

        // Lines

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(500);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(5);
        line.setOpacity(0.5);

        // Rectangles

        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(25);

        // Polygons/Triangles

        Polygon triangle = new Polygon();
        double triangleCoords = 200.0;
        double triangleCoordsTwo = 300.0;
        triangle.setStroke(Color.BLACK);
        triangle.setStrokeWidth(20);

        triangle.getPoints().setAll(
                triangleCoords,triangleCoords,
                triangleCoordsTwo,triangleCoordsTwo,
                triangleCoords,triangleCoords
                );
        triangle.setFill(Color.YELLOW);

        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        // Used images
        Image icon = new Image("file:images.png");

        // Stage configuration

        stage.setScene(scene);
        stage.setTitle("Daniel's First GUi");
        stage.getIcons().add(icon);
        stage.setWidth(750);
        stage.setHeight(750);
//        stage.setX(50);
//        stage.setY(50);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Full Screen, q to go to normal");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));


        // Stage execution
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}