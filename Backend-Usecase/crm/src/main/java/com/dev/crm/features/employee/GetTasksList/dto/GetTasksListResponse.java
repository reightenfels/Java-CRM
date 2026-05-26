package com.dev.crm.features.employee.GetTasksList.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTasksListResponse {
    private List<GetTasksListTaskDTO> tasks;
}
