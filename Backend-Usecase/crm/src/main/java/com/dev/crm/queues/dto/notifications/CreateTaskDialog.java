package com.dev.crm.queues.dto.notifications;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateTaskDialog extends BaseNotification {
    @NotBlank protected String taskName;
    @NotBlank protected Long taskId;
    @NotBlank protected Long dialogId;
}
