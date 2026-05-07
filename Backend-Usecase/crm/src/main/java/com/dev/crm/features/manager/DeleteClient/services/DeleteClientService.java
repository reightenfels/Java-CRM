package com.dev.crm.features.manager.DeleteClient.services;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.DeleteClient.contracts.DeleteClientContract;
import com.dev.crm.repositories.UserRepository;

@Service
public class DeleteClientService implements DeleteClientContract {
    private final UserRepository userRepository;

    public DeleteClientService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long deleteClient(Long clientId) {
        userRepository.deleteById(clientId);

        return clientId;
    }
}
