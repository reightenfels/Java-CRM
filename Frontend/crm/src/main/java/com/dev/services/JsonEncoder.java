package com.dev.services;

import com.dev.dto.responses.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonEncoder {
    private static final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static String encode(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T decode(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

    public static <T> ApiResponse<T> decodeApiResponse(
        String json,
        TypeReference<ApiResponse<T>> typeReference
    ) throws JsonProcessingException {
        return mapper.readValue(json, typeReference);
    }

    public static <T> ApiResponse<T> decodeApiResponse(
        String json, 
        Class<T> dataType
    ) throws JsonProcessingException {
        JavaType wrapperType = mapper.getTypeFactory()
                .constructParametricType(ApiResponse.class, dataType);
        return mapper.readValue(json, wrapperType);
    }
}
