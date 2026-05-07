package com.dev.crm.features.manager.DeleteClient;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.DeleteClient.contracts.DeleteClientContract;
import com.dev.crm.features.manager.DeleteClient.dto.DeleteClientRequest;
import com.dev.crm.features.manager.DeleteClient.dto.DeleteClientResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteClientUseCase {
    private final DeleteClientContract deleteClientService;

    public DeleteClientUseCase(DeleteClientContract deleteClientService) {
        this.deleteClientService = deleteClientService;
    }

    @Transactional
    public DeleteClientResponse handle(DeleteClientRequest request) {
        Long deletedClientId = deleteClientService.deleteClient(request.getClientId());

        // TODO: Добавить событие в очередь

        return new DeleteClientResponse(
            deletedClientId
        );
    }
}
