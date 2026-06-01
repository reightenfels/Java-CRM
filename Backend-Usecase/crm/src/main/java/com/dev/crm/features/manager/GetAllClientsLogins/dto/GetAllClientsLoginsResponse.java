package com.dev.crm.features.manager.GetAllClientsLogins.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAllClientsLoginsResponse {
    private final Set<String> clients;
}
