package com.dev.crm.features.manager.UpdateTask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateTaskResponse {
    private Long updatedTaskId;
    private int version;
}
