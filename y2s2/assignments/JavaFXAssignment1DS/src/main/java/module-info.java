module com.example.javafxassignment1ds {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxassignment1ds to javafx.fxml;
    exports com.example.javafxassignment1ds;
}