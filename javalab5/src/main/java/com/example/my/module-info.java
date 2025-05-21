module com.example.my {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.my to javafx.fxml;
    exports com.example.my;
}
