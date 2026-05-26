package com.dev.controllers.parts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DialogController {
    @FXML
    private Label chatNameLabel;

    @FXML
    private Label timestampLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Label lastSenderLabel;

    private Long dialogId;

    public void setData(
        String chatName, 
        LocalDateTime lastMessageDatetime, 
        String message, 
        String lastSenderName,
        Long dialogId
    ) {
        chatNameLabel.setText(chatName);

        if (lastMessageDatetime != null) {
            timestampLabel.setText(lastMessageDatetime.format(DateTimeFormatter.ofPattern("dd.MM.YY HH:mm")));
        }

        if (message != null) {
            messageLabel.setText(message);
        }
        
        if (lastSenderName != null) {
            lastSenderLabel.setText(lastSenderName + ":");
        }

        this.dialogId = dialogId;
    }

    public Long getDialogId() {
        return dialogId;
    }
}
