package com.dev.crm.features.manager.SendMessageToClient.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SendMessageToClientRequest {
    @NotBlank(message = "Отсутствует текст сообщения в запросе")
    private String text;

    @NotBlank(message = "Отсутствует ID диалога в запросе")
    private Long dialogId;
}
