package com.dev.crm.features.employee.OpenTaskDialog.dto;

import java.util.List;

import com.dev.crm.entities.TaskDialogMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenTaskDialogResponse {
    private List<TaskDialogMessage> messages;
}
