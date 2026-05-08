package com.dev.crm.features.manager.UpdateTask.dto;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateTaskRequest {
    @NotBlank(message = "Отсутствует ID задачи в запросе")
    private Long taskId;

    @NotBlank(message = "Отсутствует название задачи в запросе")
    private String title;

    @NotBlank(message = "Отсутствует цена задачи в запросе")
    private Long price;

    @NotBlank(message = "Отсутствует дедлайн в запросе")
    private LocalDateTime deadline;

    @NotBlank(message = "Отсутствует ID статуса в запросе")
    private Long statusId;

    @NotBlank(message = "Отсутствует ID ответственного в запросе")
    private Long responsibleId;

    private Long clientId;
    
    @NotBlank(message = "Отсутствует версия задачи в запросе")
    private int version;

    @NotBlank(message = "Отсутствует ID исполнителей задачи в запросе")
    private Set<Long> performersIds;
}
