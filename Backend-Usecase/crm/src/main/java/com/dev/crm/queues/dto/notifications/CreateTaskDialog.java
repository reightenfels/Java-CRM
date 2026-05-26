package com.dev.crm.queues.dto.notifications;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CreateTaskDialog extends BaseNotification {
    @NotBlank protected String taskName;
    @NotBlank protected Long taskId;
    @NotBlank protected Long dialogId;
}
