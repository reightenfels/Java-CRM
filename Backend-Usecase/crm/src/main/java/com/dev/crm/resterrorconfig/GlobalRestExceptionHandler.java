package com.dev.crm.resterrorconfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dev.crm.resterrorconfig.errorresponses.ErrorResponse;
import com.dev.crm.resterrorconfig.errorresponses.NotValidArgsErrorResponse;
import com.dev.crm.resterrorconfig.exceptions.ClientCreditionalsAlreadyTaken;
import com.dev.crm.resterrorconfig.exceptions.DeleteEditingEntityException;
import com.dev.crm.resterrorconfig.exceptions.LoginIsAlreadyTakenException;
import com.dev.crm.resterrorconfig.exceptions.OldEntityVersionException;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Component
@Slf4j
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
     * Обрабатывает ситуацию когда пользователь ввел неверный логин/пароль
     * @param exception
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationError(BadCredentialsException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Неверный логин/пароль");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда аккаунт пользователя отключен
     * @param exception
     * @return
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationError(DisabledException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Аккаунт отключен");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда пользователь не найден
     * @param exception
     * @return
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationError(UsernameNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда пользователь не аутентифицирован
     * @param exception
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationError(AuthenticationException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Ошибка аутентификации");
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
    public ResponseEntity<ErrorResponse> handleLoginIsAlreadyTakenError(LoginIsAlreadyTakenException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда пытаются создать клиента с уже занятыми данными
     * @param exception
     * @return
     */
    @ExceptionHandler(ClientCreditionalsAlreadyTaken.class)
    public ResponseEntity<ErrorResponse> handleClientCreditionalsAlreadyTakenError(ClientCreditionalsAlreadyTaken exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда пытаются обновить сущность, у которой есть более поздняя версия
     * @param exception
     * @return
     */
    @ExceptionHandler(OldEntityVersionException.class)
    public ResponseEntity<ErrorResponse> handleOldEntityVersionError(OldEntityVersionException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда возникла ошибка сохранения сущности в БД
     * @param exception
     * @return
     */
    @ExceptionHandler(SaveEntityException.class)
    public ResponseEntity<ErrorResponse> handleSaveEntityError(SaveEntityException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда пытаются удалить редактируемую сущность
     * @param exception
     * @return
     */
    @ExceptionHandler(DeleteEditingEntityException.class)
    public ResponseEntity<ErrorResponse> handleSaveEntityError(DeleteEditingEntityException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда тело запроса неудалось распознать
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        String message = "Некорректный формат запроса";
        
        if (exception.getCause() instanceof MismatchedInputException) {
            message = "Тело запроса не может быть пустым";
        }
        
        return ResponseEntity.badRequest().body(new ErrorResponse(message));
    }

    /**
     * Обрабатывает ситуацию когда истек срок токена авторизации
     * @param exception
     * @return
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleSaveEntityError(ExpiredJwtException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Истек срок текущего сеанса. Войдите в систему повторно");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Обрабатывает ситуацию когда на сервере произошла необработанная ошибка
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleSaveEntityError(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("На сервере произошла ошибка. Пожалуйста попробуйте позже, или свяжитесь с разработчиком");
        log.error("Ошибка: " + exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
