package com.dev.crm.features.manager.ChangeTaskStatus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChangeTaskStatusRequest {
    @NotBlank(message = "Отсутствует название статуса в запросе")
    private String statusSystemName;

    @NotNull(message = "Отсутствует версия задачи в запросе")
    private int version;
}
