package com.dev.crm.queues.dto.notifications;

import com.dev.crm.entities.Task;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class UpdateTask extends BaseNotification {
    @NotBlank protected Task updatedTask;
}
