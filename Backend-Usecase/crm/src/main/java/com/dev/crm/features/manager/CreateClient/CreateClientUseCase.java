package com.dev.crm.features.manager.CreateClient;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.CreateClient.contracts.CreateClientContract;
import com.dev.crm.features.manager.CreateClient.dto.CreateClientRequest;
import com.dev.crm.features.manager.CreateClient.dto.CreateClientResponse;
import com.dev.crm.resterrorconfig.exceptions.ClientCreditionalsAlreadyTaken;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateClientUseCase {
    private final CreateClientContract createClientService;

    public CreateClientUseCase(CreateClientContract createClientService) {
        this.createClientService = createClientService;
    }

    @Transactional
    public CreateClientResponse handle(CreateClientRequest request) {
        if (!createClientService.isClientDataUnique(request.getLogin(), request.getEmail(), request.getPhone())) {
            throw new ClientCreditionalsAlreadyTaken("Телефон, почта или логин уже заняты");
        }

        Long newClientId = createClientService.createClient(
            request.getLogin(),
            request.getPassword(),
            request.getFirstName(),
            request.getLastName(),
            request.getPhone(),
            request.getEmail()
        );

        // TODO: Добавить задачу в очередь

        return new CreateClientResponse(
            newClientId
        );
    }
}
