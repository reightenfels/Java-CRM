package com.dev.dto.models.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageModel implements ChatItem {
    private String text; 
    private String sender; 
    private LocalDateTime sendDateTime; 

    @JsonProperty("me")
    private boolean me;
    
    private String avatarUrl;

    public MessageModel() {}

    public MessageModel(
        String text,
        String sender,
        LocalDateTime sendDateTime,
        boolean me,
        String avatarUrl
    ) {
        this.text = text; 
        this.sender = sender; 
        this.sendDateTime = sendDateTime; 
        this.me = me;
        this.avatarUrl = avatarUrl;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public LocalDateTime getSendDateTime() {
        return sendDateTime;
    }

    @JsonProperty("me")
    public Boolean isMe() {
        return me;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("me")
    public void setMe(boolean me) {
        this.me = me;
    }
}
