package com.dev.crm.features.manager.OpenClientCard;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.User;
import com.dev.crm.features.manager.OpenClientCard.contracts.OpenClientCardContract;
import com.dev.crm.features.manager.OpenClientCard.dto.OpenClientCardResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenClientCardUseCase {
    private final OpenClientCardContract openClientService;

    public OpenClientCardUseCase(OpenClientCardContract openClientService) {
        this.openClientService = openClientService;
    }
    
    @Transactional
    public OpenClientCardResponse handle(Long clientId) {
        User client = openClientService.getClientById(clientId);

        return new OpenClientCardResponse(
            client.getLogin(),
            client.getFirstName(),
            client.getLastName(),
            client.getPhone(),
            client.getEmail()
        );
    }
}
