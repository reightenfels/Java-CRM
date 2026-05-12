package com.dev.crm.resterrorconfig.errorresponses;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    protected Boolean success;
    protected String message;
    protected Map<String, String> errors = new HashMap<>();

    public ErrorResponse(String message) {
        this.success = false;
        this.message = message;
        errors.put("error", message);
    }
}
