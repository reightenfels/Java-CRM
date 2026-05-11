module com.dev {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;

    opens com.dev.controllers to javafx.fxml;

    opens com.dev to javafx.fxml;
    exports com.dev;
}
