module com.nightbreeze {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires atlantafx.base;

    opens com.nightbreeze to javafx.fxml;
    opens com.nightbreeze.controller to javafx.fxml;
    opens com.nightbreeze.model to javafx.base, com.fasterxml.jackson.databind;
    opens com.nightbreeze.controller.characterCreation to javafx.fxml;
    opens com.nightbreeze.util to javafx.base;

    exports com.nightbreeze;
    exports com.nightbreeze.model;
    exports com.nightbreeze.util;
}
