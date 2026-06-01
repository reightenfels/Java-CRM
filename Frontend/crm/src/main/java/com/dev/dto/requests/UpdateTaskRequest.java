package com.dev.dto.requests;

import java.time.LocalDateTime;
import java.util.Set;

public record UpdateTaskRequest (
    Long taskId,
    String title,
    String description,
    Long price,
    LocalDateTime deadline,
    String statusDisplayName,
    String responsibleLogin,
    String clientLogin,
    int version,
    Set<String> employeesLogins
 ) {

 }
