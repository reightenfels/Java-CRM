package com.dev.crm.features.employee.SendMessageToEmployee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SendMessageToEmployeeResponse {
    private Long newMessageId;
    private Long dialogId;
}
