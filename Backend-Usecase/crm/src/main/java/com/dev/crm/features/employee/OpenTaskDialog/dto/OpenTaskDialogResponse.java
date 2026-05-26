package com.dev.crm.features.employee.OpenTaskDialog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenTaskDialogResponse {
    private List<OpenTaskDialogMessageDTO> messages;
}
