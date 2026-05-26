package com.dev.crm.features.manager.GetManagersList.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetManagersListResponse {
    private final List<String> managers;
}
