package com.dev.controllers.parts;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MessageController {
    @FXML
    private Label messageText;

    @FXML
    private Label messageSenderName;

    public void setData(String messageText, String messageSenderName) {
        this.messageText.setText(messageText);
        this.messageSenderName.setText(messageSenderName);
    }
}
