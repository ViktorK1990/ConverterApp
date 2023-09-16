module com.example.convertorapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.convertorapp to javafx.fxml;
    exports com.example.convertorapp;
}