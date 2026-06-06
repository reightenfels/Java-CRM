package com.dev.dto.models;

import java.time.LocalDateTime;

public class DialogModel {
    private Long dialogId;
    private String chatName;
    private LocalDateTime lastMessageDatetime;
    private String message;
    private String lastSenderName;
    private Long taskId;
    private int taskVersion;
    private String taskSystemName;

    public DialogModel() {}

    public DialogModel(
        Long dialogId,
        String chatName, 
        LocalDateTime lastMessageDatetime, 
        String message, 
        String lastSenderName,
        Long taskId,
        int taskVersion,
        String taskSystemName
    ) {
        this.dialogId = dialogId;
        this.chatName = chatName;
        this.lastMessageDatetime = lastMessageDatetime;
        this.message = message;
        this.lastSenderName = lastSenderName;
        this.taskId = taskId;
        this.taskVersion = taskVersion;
        this.taskSystemName = taskSystemName;
    }

    public Long getDialogId() { return dialogId; }
    public String getChatName() { return chatName; }
    public LocalDateTime getLastMessageDatetime() { return lastMessageDatetime; }
    public String getMessage() { return message; }
    public String getLastSenderName() { return lastSenderName; }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public int getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(int taskVersion) {
        this.taskVersion = taskVersion;
    }

    public String getTaskSystemName() {
        return taskSystemName;
    }
}
