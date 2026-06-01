package com.dev.crm.features.manager.OpenTaskCard.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OpenTaskCardDTO {
    private final Long id;
    private final String title;
    private final String description;
    private final Long price;
    private final LocalDateTime deadline;
    private final String statusDisplayName;
    private final String managerLogin;
    private final String clientLogin;
    private final int version;
    private final Set<String> employees;
}
