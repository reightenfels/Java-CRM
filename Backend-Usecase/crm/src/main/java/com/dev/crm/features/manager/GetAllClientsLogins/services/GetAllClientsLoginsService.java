package com.dev.crm.features.manager.GetAllClientsLogins.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.GetAllClientsLogins.contracts.GetAllClientsLoginsContract;
import com.dev.crm.repositories.UserRepository;

@Service
public class GetAllClientsLoginsService implements GetAllClientsLoginsContract {
    public final UserRepository userRepository;

    public GetAllClientsLoginsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<String> getAllClientsLogins() {
        return userRepository.findAllClients();
    }
}
