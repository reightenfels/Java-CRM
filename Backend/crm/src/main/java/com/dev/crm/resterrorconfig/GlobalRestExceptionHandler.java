package com.dev.crm.resterrorconfig;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dev.crm.resterrorconfig.errorresponses.ErrorResponse;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
