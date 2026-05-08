package com.dev.crm.queues.dto.notifications;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class DeleteEmployeeDialog extends BaseNotification {
    @NotBlank protected Long dialogId;
}