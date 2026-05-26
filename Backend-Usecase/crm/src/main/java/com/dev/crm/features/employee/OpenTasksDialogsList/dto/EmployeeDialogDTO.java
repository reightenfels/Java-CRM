package com.dev.crm.features.employee.OpenTasksDialogsList.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmployeeDialogDTO {
    private final String chatName;
    private final String message;
    private final LocalDateTime lastMessageDatetime;
    private final String lastSenderName;
}
