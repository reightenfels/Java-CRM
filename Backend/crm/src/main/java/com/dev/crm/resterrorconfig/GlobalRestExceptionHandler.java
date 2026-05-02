package com.dev.crm.resterrorconfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dev.crm.resterrorconfig.errorresponses.ErrorResponse;
import com.dev.crm.resterrorconfig.errorresponses.NotValidArgsErrorResponse;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NotValidArgsErrorResponse> handleNoValidArgs(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        Optional.ofNullable(exception.getBindingResult().getAllErrors()).ifPresent(errorsList -> {
            errorsList.forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        });    

        NotValidArgsErrorResponse errorResponse = new NotValidArgsErrorResponse(
            "Ошибка в содержимом параметров",
            errors
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
