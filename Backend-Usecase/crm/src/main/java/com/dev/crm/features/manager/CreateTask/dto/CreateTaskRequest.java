package com.dev.crm.features.manager.CreateTask.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateTaskRequest {
    private String title;

    @NotNull(message = "Отсутствует цена в запросе")
    private Long price;

    @NotNull(message = "Отсутствует дедлайн в запросе")
    private LocalDateTime deadline;

    @NotNull(message = "Отсутствует ID статуса в запросе")
    private Long statusId;

    @NotNull(message = "Отсутствует ID ответственного в запросе")
    private Long responsibleId;

    private Long clientId;
}
