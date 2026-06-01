package com.dev.crm.features.manager.GetAllEmployeesLogins.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.crm.features.manager.GetAllEmployeesLogins.contracts.GetAllEmployeesLoginsContract;
import com.dev.crm.repositories.UserRepository;

@Service
public class GetAllEmployeesLoginsService implements GetAllEmployeesLoginsContract {
    public final UserRepository userRepository;

    public GetAllEmployeesLoginsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllEmployeesLogins() {
        return userRepository.findAllEmployees();
    }
}
