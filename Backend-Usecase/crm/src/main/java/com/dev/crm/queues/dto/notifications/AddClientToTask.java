package com.dev.crm.queues.dto.notifications;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class AddClientToTask extends BaseNotification {
    @NotBlank protected Long taskId;
    @NotBlank protected Long newClientId;
    @NotBlank protected String newClientLogin;
}
