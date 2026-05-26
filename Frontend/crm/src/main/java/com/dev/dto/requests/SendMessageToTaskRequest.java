package com.dev.dto.requests;

public record SendMessageToTaskRequest (
    String text,
    Long dialogId
) {
    
}
