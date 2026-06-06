package com.dev.crm.features.manager.DeleteTask.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeleteTaskRequest {
    @NotNull(message = "Отсутствует версия в запросе")
    private int version;
}
