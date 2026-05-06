package com.dev.crm.features.employee.OpenEmployeeDialogsList.dto;

import java.util.List;

import com.dev.crm.entities.PersonalEmployeeDialog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenEmployeeDialogsListResponse {
    private List<PersonalEmployeeDialog> dialogs;
}
