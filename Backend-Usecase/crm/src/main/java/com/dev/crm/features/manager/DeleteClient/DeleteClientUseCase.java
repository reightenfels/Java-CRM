package com.dev.crm.features.manager.DeleteClient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.DeleteClient.contracts.DeleteClientContract;
import com.dev.crm.features.manager.DeleteClient.dto.DeleteClientRequest;
import com.dev.crm.features.manager.DeleteClient.dto.DeleteClientResponse;
import com.dev.crm.features.manager.DeleteClient.services.DeleteClientNotificationService;

@Service
public class DeleteClientUseCase {
    private final DeleteClientContract deleteClientService;
    private final DeleteClientNotificationService notificationService;

    public DeleteClientUseCase(
        DeleteClientContract deleteClientService,
        DeleteClientNotificationService notificationService
    ) {
        this.deleteClientService = deleteClientService;
        this.notificationService = notificationService;
    }

    @Transactional
    public DeleteClientResponse handle(DeleteClientRequest request) {
        Long deletedClientId = deleteClientService.deleteClient(request.getClientId());

        notificationService.send(deletedClientId);

        return new DeleteClientResponse(
            deletedClientId
        );
    }
}
