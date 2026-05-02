package com.dev.crm.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUsersRequest {
    @NotBlank(message = "Поиск не может быть пустым")
    private String search;
}
