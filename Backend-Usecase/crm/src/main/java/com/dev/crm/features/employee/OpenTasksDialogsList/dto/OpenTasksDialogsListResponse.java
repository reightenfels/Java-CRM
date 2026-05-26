package com.dev.crm.features.employee.OpenTasksDialogsList.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenTasksDialogsListResponse {
    private List<EmployeeDialogDTO> dialogs;
}
