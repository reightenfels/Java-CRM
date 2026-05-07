package com.dev.crm.features.manager.CloseTask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CloseTaskRequest {
    @NotBlank(message = "Отсутствует ID задачи в запросе")
    private Long taskId;
}
