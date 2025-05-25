module com.example.lab23 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab23 to javafx.fxml;
    exports com.example.lab23;
}