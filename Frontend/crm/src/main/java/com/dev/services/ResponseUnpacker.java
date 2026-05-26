package com.dev.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ResponseUnpacker {
    public static <T> T extract(String body, Class<T> clazz) {
        try {
            return JsonEncoder.decodeApiResponse(body, clazz).data;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to parse API response", e);
        }
    }
}
