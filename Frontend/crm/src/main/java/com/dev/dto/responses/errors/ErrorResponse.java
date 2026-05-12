package com.dev.dto.responses.errors;

public class ErrorResponse {
    public String message;
    public Boolean success;

    public ErrorResponse() {

    }

    public ErrorResponse(String message) {
        this.message = message;
    }
}
