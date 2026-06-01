package com.dev.crm.features.manager.CreateTask.dto;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateTaskRequest {
    @NotBlank(message = "Отсутствует название задачи в запросе")
    private String title;

    private String description;

    @NotNull(message = "Отсутствует цена задачи в запросе")
    private Long price;

    @NotNull(message = "Отсутствует дедлайн в запросе")
    private LocalDateTime deadline;

    @NotNull(message = "Отсутствует название статуса в запросе")
    private String statusDisplayName;

    @NotNull(message = "Отсутствует логин ответственного в запросе")
    private String responsibleLogin;

    private String clientLogin;

    private Set<String> employeesLogins;
}
