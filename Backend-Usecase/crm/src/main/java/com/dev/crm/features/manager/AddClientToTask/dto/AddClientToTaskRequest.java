package com.dev.crm.features.manager.AddClientToTask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddClientToTaskRequest {
    @NotBlank(message = "Отсутствует ID задачи в запросе")
    private Long taskId;

    @NotBlank(message = "Отсутствует ID клиента в запросе")
    private Long clientId;

    @NotBlank(message = "Отсутствует версия задачи в запросе")
    private int version;
}
