module com.example.aswincsd214lab2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.aswincsd214lab2 to javafx.fxml;
    exports com.example.aswincsd214lab2;
}