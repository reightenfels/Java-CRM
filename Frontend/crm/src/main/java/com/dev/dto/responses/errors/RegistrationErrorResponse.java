package com.dev.dto.responses.errors;

import java.util.Map;

public class RegistrationErrorResponse extends ErrorResponse {
    public Map<String, String> errors;

    public RegistrationErrorResponse() {
        
    }

    public RegistrationErrorResponse(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
