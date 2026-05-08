package com.dev.crm.features.manager.DeleteTask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeleteTaskRequest {
    @NotBlank(message = "Отсутствует ID задачи в запросе")
    private Long taskId;

    @NotBlank(message = "Отсутствует версия в запросе")
    private int version;
}
