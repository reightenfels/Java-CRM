package com.dev.crm.features.employee.SendMessageToEmployee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SendMessageToEmployeeRequest {
    @NotBlank(message = "Отсутствует содержимое сообщения в запросе")
    private String text;

    private Long dialogId;

    @NotBlank(message = "Отсутствует ID получателя в запросе")
    private Long recipientId;
}
