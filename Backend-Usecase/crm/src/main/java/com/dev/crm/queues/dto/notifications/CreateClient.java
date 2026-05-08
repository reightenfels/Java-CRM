package com.dev.crm.queues.dto.notifications;

import com.dev.crm.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateClient extends BaseNotification {
    @NotBlank protected User newClient;
}
