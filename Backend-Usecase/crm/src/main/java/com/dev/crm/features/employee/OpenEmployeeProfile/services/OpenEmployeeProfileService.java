package com.dev.crm.features.employee.OpenEmployeeProfile.services;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.User;
import com.dev.crm.features.employee.OpenEmployeeProfile.contracts.OpenEmployeeProfileContract;
import com.dev.crm.features.employee.OpenEmployeeProfile.dto.OpenEmployeeProfileDTO;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class OpenEmployeeProfileService implements OpenEmployeeProfileContract {
    private final UserRepository userRepository;

    public OpenEmployeeProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OpenEmployeeProfileDTO getEmployeeByLogin(String login) {
        User employee = userRepository.findByLogin(login).orElseThrow(
            () -> new ResourceNotFoundException("Не найден пользователь с логином: " + login)
        );

        return new OpenEmployeeProfileDTO(
            employee.getFirstName(),
            employee.getLastName(),
            employee.getPhone(),
            employee.getEmail()
        );
    }
}
