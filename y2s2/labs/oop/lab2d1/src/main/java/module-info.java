module com.example.lab2d1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab2d1 to javafx.fxml;
    exports com.example.lab2d1;
}