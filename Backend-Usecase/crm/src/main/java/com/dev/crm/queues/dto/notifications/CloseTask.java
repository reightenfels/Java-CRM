package com.dev.crm.queues.dto.notifications;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CloseTask extends BaseNotification {
    @NotBlank protected Long taskId;
    protected Set<String> editorsLogins;
}
