package com.dev.dto.requests;

public record RegistrationRequest (
    String login,
    String password,
    String firstName,
    String lastName,
    String phone,
    String email
) {}
