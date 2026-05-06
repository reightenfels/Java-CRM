package com.dev.crm.resterrorconfig.errorresponses;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotValidArgsErrorResponse extends ErrorResponse {
    protected Map<String, String> errors = new HashMap<>();

    public NotValidArgsErrorResponse(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
