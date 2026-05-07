package com.dev.crm.features.manager.ChangeTaskStatus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChangeTaskStatusRequest {
    @NotBlank(message = "Отсутствует ID задачи в запросе")
    private Long taksId;

    @NotBlank(message = "Отсутствует ID статуса в запросе")
    private Long statusId;

    @NotBlank(message = "Отсутствует версия задачи в запросе")
    private int version;
}
