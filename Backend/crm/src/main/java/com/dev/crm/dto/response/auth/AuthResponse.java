package com.dev.crm.dto.response.auth;

public record AuthResponse (
    String accessToken,
    String refreshToken
) {
    
}
