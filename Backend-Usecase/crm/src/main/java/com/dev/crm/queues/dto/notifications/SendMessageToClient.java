package com.dev.crm.queues.dto.notifications;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SendMessageToClient extends BaseNotification {
    protected @NotBlank String text;
    protected @NotBlank Long messageId;
    protected @NotBlank LocalDateTime sendingDateTime;
    protected @NotBlank Long senderId;
    protected @NotBlank Long dialogId;
}
