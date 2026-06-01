package com.dev.crm.features.manager.GetAllClientsLogins;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.GetAllClientsLogins.contracts.GetAllClientsLoginsContract;
import com.dev.crm.features.manager.GetAllClientsLogins.dto.GetAllClientsLoginsResponse;

@Service
public class GetAllClientsLoginsUseCase {
    private final GetAllClientsLoginsContract clientsService;

    public GetAllClientsLoginsUseCase(GetAllClientsLoginsContract clientsService) {
        this.clientsService = clientsService;
    }

    @Transactional
    public GetAllClientsLoginsResponse handle() {
        Set<String> clients = clientsService.getAllClientsLogins();

        return new GetAllClientsLoginsResponse(
            clients
        );
    }
}
