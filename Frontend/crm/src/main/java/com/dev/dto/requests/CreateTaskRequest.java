package com.dev.dto.requests;

import java.time.LocalDateTime;
import java.util.Set;

public record CreateTaskRequest (
    String title,
    String description,
    Long price,
    LocalDateTime deadline,
    String statusDisplayName,
    String responsibleLogin,
    String clientLogin,
    Set<String> employeesLogins
) {
    
}
