package com.dev.dto.models.message;

public record MessageModel (
    String text, 
    String sender, 
    String timestamp, 
    boolean isMe,
    String avatarUrl
) implements ChatItem {
    
}
