package com.dev.crm.features.employee.SendMessageToTask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SendMessageToTaskRequest {
    @NotBlank(message = "Отсутствует содержимое сообщения в запросе")
    private String text;

    @NotBlank(message = "Отсутствует ID диалога в запросе")
    private Long dialogId;
}
