package com.dev.crm.features.employee.GetAllStatuses.dto;

import java.util.List;

import com.dev.crm.entities.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetAllStatusesResponse {
    private List<TaskStatus> statusesList;
}
