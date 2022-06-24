module com.example.demop2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demop2 to javafx.fxml;
    exports com.example.demop2;
}