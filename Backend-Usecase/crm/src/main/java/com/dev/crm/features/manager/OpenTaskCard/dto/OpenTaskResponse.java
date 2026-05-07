package com.dev.crm.features.manager.OpenTaskCard.dto;

import com.dev.crm.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenTaskResponse {
    private Task task;
}
