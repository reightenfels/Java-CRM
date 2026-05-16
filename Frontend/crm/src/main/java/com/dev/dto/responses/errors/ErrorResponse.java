package com.dev.dto.responses.errors;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    public String message;
    public Boolean success;
    public Map<String, String> errors = new HashMap<>();

    public ErrorResponse() {

    }

    public ErrorResponse(String message) {
        this.message = message;
        errors.put("error", message);
    }
}
