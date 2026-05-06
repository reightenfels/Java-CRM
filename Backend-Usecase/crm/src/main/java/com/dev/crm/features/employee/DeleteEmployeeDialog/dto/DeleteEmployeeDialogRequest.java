package com.dev.crm.features.employee.DeleteEmployeeDialog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeleteEmployeeDialogRequest {
    @NotBlank(message = "Отсутствует ID диалога в запросе")
    private Long dialogId;
}
