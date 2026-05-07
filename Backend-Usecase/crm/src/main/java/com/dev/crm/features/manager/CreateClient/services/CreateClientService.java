package com.dev.crm.features.manager.CreateClient.services;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.crm.entities.Role;
import com.dev.crm.entities.User;
import com.dev.crm.features.manager.CreateClient.contracts.CreateClientContract;
import com.dev.crm.repositories.RoleRepository;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;

@Service
public class CreateClientService implements CreateClientContract {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateClientService(
        UserRepository userRepository,
        RoleRepository roleRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean isClientDataUnique(
        String login,
        String email,
        String phone
    ) {
        List<User> usersWithSameCreditionals = userRepository.findByLoginOrPhoneOrEmail(login, phone, email);

        return usersWithSameCreditionals.isEmpty();
    }

    @Override
    public Long createClient(
        String login,
        String password,
        String firstName,
        String lastName,
        String phone,
        String email
    ) {
        User client = new User();
        client.setLogin(login);
        client.setPassword(passwordEncoder.encode(password));
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);
        client.setEmail(email);

        Role role = roleRepository.findBySystemName("ROLE_CLIENT").orElseThrow(
            () -> new ResourceNotFoundException("Не найдена роль клиента")
        );

        client.setRole(role);
        client.setTasks(Set.of());

        try {
            userRepository.save(client);
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка при создании клиента: " + e.getMessage());
        }

        return client.getId();
    }
}
