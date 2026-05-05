package com.dev.crm.resterrorconfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dev.crm.resterrorconfig.errorresponses.ErrorResponse;
import com.dev.crm.resterrorconfig.errorresponses.NotValidArgsErrorResponse;
import com.dev.crm.resterrorconfig.exceptions.LoginIsAlreadyTakenException;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    /**
     * Обрабатывает ситуацию когда сущность не найдена
     * @param exception
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда запрос не прошел валидацию
     * @param exception
     * @return
     */
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

    /**
     * Обрабатывает ситуацию когда пользователь не аутентифицировать
     * @param exception
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationError(AuthenticationException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Необходима аутентификация");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда пользователь не авторизован
     * @param exception
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedError(AccessDeniedException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Доступ запрещён");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда пытаются зарегистрироваться на уже существующий логин
     * @param exception
     * @return
     */
    @ExceptionHandler(LoginIsAlreadyTakenException.class)
    public ResponseEntity<ErrorResponse> handleLoginIsAlreadyTakenResponse(LoginIsAlreadyTakenException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Логин уже занят");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
