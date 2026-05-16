package com.dev.services;

import com.dev.dto.responses.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class ResponseUnpacker {
    public static <T> T extract(String body, Class<T> dataClass) {
        TypeReference<ApiResponse<T>> typeRef = new TypeReference<>() {};
        try {
            ApiResponse<T> response = JsonEncoder.decodeApiResponse(body, typeRef);
            return response.data;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse API response", e);
        }
    }
}
