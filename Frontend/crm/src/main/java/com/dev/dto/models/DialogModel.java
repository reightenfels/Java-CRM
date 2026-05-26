package com.dev.dto.models;

import java.time.LocalDateTime;

public class DialogModel {
    private Long dialogId;
    private String chatName;
    private LocalDateTime lastMessageDatetime;
    private String message;
    private String lastSenderName;

    public DialogModel() {}

    public DialogModel(
        Long dialogId,
        String chatName, 
        LocalDateTime lastMessageDatetime, 
        String message, 
        String lastSenderName
    ) {
        this.dialogId = dialogId;
        this.chatName = chatName;
        this.lastMessageDatetime = lastMessageDatetime;
        this.message = message;
        this.lastSenderName = lastSenderName;
    }

    public Long getDialogId() { return dialogId; }
    public String getChatName() { return chatName; }
    public LocalDateTime getLastMessageDatetime() { return lastMessageDatetime; }
    public String getMessage() { return message; }
    public String getLastSenderName() { return lastSenderName; }
}
