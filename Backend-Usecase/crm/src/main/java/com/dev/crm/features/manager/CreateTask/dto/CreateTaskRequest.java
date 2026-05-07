package com.dev.crm.features.manager.CreateTask.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateTaskRequest {
    @NotBlank(message = "Отсутствует название в запросе")
    private String title;

    @NotBlank(message = "Отсутствует цена в запросе")
    private Long price;

    @NotBlank(message = "Отсутствует дедлайн в запросе")
    private LocalDateTime deadline;

    @NotBlank(message = "Отсутствует ID статуса в запросе")
    private Long statusId;

    @NotBlank(message = "Отсутствует ID ответственного в запросе")
    private Long responsibleId;

    private Long clientId;
}
