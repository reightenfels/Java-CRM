package com.dev.dto.responses;

public class ApiResponse<T> {
    public Boolean success;
    public String message;
    public T data;

    public ApiResponse() {
        
    }
}
