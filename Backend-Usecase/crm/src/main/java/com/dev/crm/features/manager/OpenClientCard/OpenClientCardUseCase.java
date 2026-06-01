package com.dev.crm.features.manager.OpenClientCard;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.OpenClientCard.contracts.OpenClientCardContract;
import com.dev.crm.features.manager.OpenClientCard.dto.OpenClientCardDTO;
import com.dev.crm.features.manager.OpenClientCard.dto.OpenClientCardResponse;

@Service
public class OpenClientCardUseCase {
    private final OpenClientCardContract openClientService;

    public OpenClientCardUseCase(OpenClientCardContract openClientService) {
        this.openClientService = openClientService;
    }
    
    @Transactional
    public OpenClientCardResponse handle(String clientLogin) {
        OpenClientCardDTO client = openClientService.getClientByLogin(clientLogin);

        return new OpenClientCardResponse(
            client
        );
    }
}
