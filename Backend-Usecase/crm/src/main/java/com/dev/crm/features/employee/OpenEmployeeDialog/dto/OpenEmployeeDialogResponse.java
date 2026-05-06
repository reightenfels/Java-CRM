package com.dev.crm.features.employee.OpenEmployeeDialog.dto;

import java.util.List;

import com.dev.crm.entities.PersonalEmployeeDialogMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenEmployeeDialogResponse {
    private List<PersonalEmployeeDialogMessage> messages;
}
