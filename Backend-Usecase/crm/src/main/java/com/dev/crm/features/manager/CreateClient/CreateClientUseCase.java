package com.dev.crm.features.manager.CreateClient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.CreateClient.contracts.CreateClientContract;
import com.dev.crm.features.manager.CreateClient.dto.CreateClientRequest;
import com.dev.crm.features.manager.CreateClient.dto.CreateClientResponse;
import com.dev.crm.features.manager.CreateClient.services.CreateClientNotificationService;
import com.dev.crm.resterrorconfig.exceptions.ClientCreditionalsAlreadyTaken;

@Service
public class CreateClientUseCase {
    private final CreateClientContract createClientService;
    private final CreateClientNotificationService notificationService;

    public CreateClientUseCase(
        CreateClientContract createClientService,
        CreateClientNotificationService notificationService
    ) {
        this.createClientService = createClientService;
        this.notificationService = notificationService;
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

        notificationService.send(newClientId);

        return new CreateClientResponse(
            newClientId
        );
    }
}
