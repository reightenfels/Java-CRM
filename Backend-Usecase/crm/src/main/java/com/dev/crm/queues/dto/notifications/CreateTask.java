package com.dev.crm.queues.dto.notifications;

import com.dev.crm.entities.Task;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateTask extends BaseNotification {
    @NotBlank protected Task newTask;
}
