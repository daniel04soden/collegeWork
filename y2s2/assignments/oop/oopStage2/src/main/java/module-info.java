module com.example.oopstage2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oopstage2 to javafx.fxml;
    exports com.example.oopstage2;
}