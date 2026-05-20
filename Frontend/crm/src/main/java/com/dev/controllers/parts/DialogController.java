package com.dev.controllers.parts;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DialogController {
    @FXML
    private Label chatNameLabel;
    @FXML
    private Label timestampLabel;
    @FXML
    private Label messageLabel;

    public void setData(String chatName, String timestamp, String message) {
        chatNameLabel.setText(chatName);
        timestampLabel.setText(timestamp);
        messageLabel.setText(message);
    }
}
