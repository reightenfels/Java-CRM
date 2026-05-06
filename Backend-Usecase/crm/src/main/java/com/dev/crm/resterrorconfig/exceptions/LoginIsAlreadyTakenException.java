package com.dev.crm.resterrorconfig.exceptions;

public class LoginIsAlreadyTakenException extends RuntimeException {
    public LoginIsAlreadyTakenException(String message) {
        super(message);
    }
}
