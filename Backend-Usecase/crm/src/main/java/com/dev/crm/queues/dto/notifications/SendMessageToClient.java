package com.dev.crm.queues.dto.notifications;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class SendMessageToClient extends BaseNotification {
    protected @NotBlank String text;
    protected @NotBlank Long messageId;
    protected @NotBlank LocalDateTime sendingDateTime;
    protected @NotBlank Long senderId;
    protected @NotBlank Long dialogId;
}
