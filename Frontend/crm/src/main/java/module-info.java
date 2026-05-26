module com.dev {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires javafx.base;
    requires com.fasterxml.jackson.core;
    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.dev.controllers.parts to javafx.fxml;
    opens com.dev.controllers to javafx.fxml;
    opens com.dev.dto.requests to com.fasterxml.jackson.databind;
    opens com.dev.dto.responses.errors to com.fasterxml.jackson.databind;
    opens com.dev.dto.responses.success to com.fasterxml.jackson.databind;
    opens com.dev.dto.responses to com.fasterxml.jackson.databind;
    opens com.dev.dto.models to com.fasterxml.jackson.databind;

    opens com.dev to javafx.fxml;
    exports com.dev;
}
