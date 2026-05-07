package com.dev.crm.features.manager.OpenClientCard.services;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.User;
import com.dev.crm.features.manager.OpenClientCard.contracts.OpenClientCardContract;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class OpenClientCardService implements OpenClientCardContract {
    private final UserRepository userRepository;

    public OpenClientCardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getClientById(Long clientId) {
        return userRepository.findById(clientId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден клиент с ID: " + clientId)
        );
    }
}
