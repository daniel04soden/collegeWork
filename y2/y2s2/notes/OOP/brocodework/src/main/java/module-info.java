module com.example.brocodework {
    requires javafx.controls;
    requires javafx.fxml;


    opens Main to javafx.fxml;
    exports Main;
}