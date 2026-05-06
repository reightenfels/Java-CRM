package com.dev.crm.features.employee.OpenTasksDialogsList.dto;

import java.util.List;

import com.dev.crm.entities.TaskDialog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenTasksDialogsListResponse {
    private List<TaskDialog> dialogs;
}
