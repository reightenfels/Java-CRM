package com.dev.crm.features.manager.CreateTask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateTaskResponse {
    private Long newTaskId;
    private Long newDialogId;
}
