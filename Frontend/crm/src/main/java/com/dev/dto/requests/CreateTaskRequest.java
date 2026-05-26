package com.dev.dto.requests;

import java.time.LocalDateTime;

public record CreateTaskRequest (
    String title,
    Long price,
    LocalDateTime deadline,
    Long statusId,
    Long responsibleId
) {
    
}
