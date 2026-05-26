package com.dev.crm.queues.dto.notifications;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class OpenTask extends BaseNotification {
    @NotBlank protected Long taskId;
    protected Set<String> taskEditorsLogins;
}
