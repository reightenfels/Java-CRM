package com.dev.crm.queues.dto.notifications;

import com.dev.crm.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CreateClient extends BaseNotification {
    @NotBlank protected User newClient;
}
