module com.example.vilabfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.vilabfx to javafx.fxml;
    exports com.example.vilabfx;
    exports com.example.vilabfx.controllers;
    opens com.example.vilabfx.controllers to javafx.fxml;
}