package com.dev.crm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(
            true,
            "Операция выполнена успешно",
            data
        );
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(
            true,
            message,
            data
        );
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(
            false, 
            message, 
            null
        );
    }
}
