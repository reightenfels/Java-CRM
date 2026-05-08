package com.dev.crm.queues.dto.notifications;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AddClientToTask extends BaseNotification {
    @NotBlank protected Long taskId;
    @NotBlank protected Long newClientId;
    @NotBlank protected String newClientLogin;
}
