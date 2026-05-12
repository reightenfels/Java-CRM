package com.dev.crm.dto.command.auth;

public record RegisterUserCommand (
    String login,
    String password,
    String firstName,
    String lastName,
    String phone,
    Long roleId,
    String email
) {
    
}
