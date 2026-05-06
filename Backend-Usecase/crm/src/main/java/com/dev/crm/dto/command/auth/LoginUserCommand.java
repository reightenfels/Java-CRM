package com.dev.crm.dto.command.auth;

public record LoginUserCommand (
    String login,
    String password
) {
    
}
