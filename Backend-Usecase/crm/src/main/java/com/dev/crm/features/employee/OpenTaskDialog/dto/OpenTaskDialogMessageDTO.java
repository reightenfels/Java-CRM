package com.dev.crm.features.employee.OpenTaskDialog.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OpenTaskDialogMessageDTO {
    private final String text; 
    private final String sender; 
    private final LocalDateTime sendDateTime; 
    private final Boolean me;
    private final String avatarUrl;
}
