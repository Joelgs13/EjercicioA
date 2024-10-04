module com.example.ejercicioa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicioa to javafx.fxml;
    exports com.example.ejercicioa;
}