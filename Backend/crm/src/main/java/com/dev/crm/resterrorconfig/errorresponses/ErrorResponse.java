package com.dev.crm.resterrorconfig.errorresponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    protected String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
