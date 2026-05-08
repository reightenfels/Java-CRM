package com.dev.crm.queues.dto.notifications;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class DeleteClient extends BaseNotification {
    @NotBlank protected Long deletedClientId;
}
