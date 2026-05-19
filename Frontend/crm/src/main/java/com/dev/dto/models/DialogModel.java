package com.dev.dto.models;

public class DialogModel {
    private final String chatName;
    private final String timestamp;
    private final String message;

    public DialogModel(String chatName, String timestamp, String message) {
        this.chatName = chatName;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getChatName() { return chatName; }
    public String getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
}
