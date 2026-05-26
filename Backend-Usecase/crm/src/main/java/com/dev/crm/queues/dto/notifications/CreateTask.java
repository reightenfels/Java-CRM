package com.dev.crm.queues.dto.notifications;

import com.dev.crm.entities.Task;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CreateTask extends BaseNotification {
    @NotBlank protected Task newTask;
}
