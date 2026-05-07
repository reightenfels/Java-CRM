package com.dev.crm.features.manager.DeleteClient.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeleteClientRequest {
    @NotBlank(message = "Отсутствует ID клиента в запросе")
    private long clientId;
}
