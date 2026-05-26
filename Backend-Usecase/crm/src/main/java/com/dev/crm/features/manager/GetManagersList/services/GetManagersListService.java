package com.dev.crm.features.manager.GetManagersList.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.GetManagersList.contracts.GetManagersListContract;
import com.dev.crm.repositories.UserRepository;

@Service
public class GetManagersListService implements GetManagersListContract {
    private final UserRepository userRepository;

    public GetManagersListService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllManagersLogins() {
        List<String> managersLogins = userRepository.findAllManagers();

        return managersLogins;
    } 
}
