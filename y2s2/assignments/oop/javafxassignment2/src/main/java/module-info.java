module com.example.javafxassignment1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jdk.compiler;

    opens com.example.javafxassignment1 to javafx.fxml;
    exports com.example.javafxassignment1;
    exports com.example.javafxassignment1.Models;
    opens com.example.javafxassignment1.Models to javafx.fxml;
    exports com.example.javafxassignment1.Controllers;
    opens com.example.javafxassignment1.Controllers to javafx.fxml;
}