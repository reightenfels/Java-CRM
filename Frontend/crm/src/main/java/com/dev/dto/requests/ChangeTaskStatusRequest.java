package com.dev.dto.requests;

public record ChangeTaskStatusRequest (
    String statusSystemName,
    int version
) {
    
}
