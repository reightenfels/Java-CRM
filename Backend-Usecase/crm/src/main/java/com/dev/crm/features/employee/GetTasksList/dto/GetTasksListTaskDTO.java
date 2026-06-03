package com.dev.crm.features.employee.GetTasksList.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetTasksListTaskDTO {
    private final Long id;
    private final String title;
    private final Long price;
    private final LocalDateTime deadline;
    private final String managerLogin; 
    private final String clientLogin;
    private final Long statusId;
    private final Long employeeDialogId;
}
