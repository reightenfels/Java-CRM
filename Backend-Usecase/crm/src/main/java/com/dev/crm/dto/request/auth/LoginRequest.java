package com.dev.crm.dto.request.auth;

public record LoginRequest (
    String login,
    String password
) {
    
}
