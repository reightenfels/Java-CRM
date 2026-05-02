package com.dev.crm.services.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.crm.dto.command.user.CreateUserCommand;
import com.dev.crm.dto.command.user.UpdateUserCommand;
import com.dev.crm.entities.Role;
import com.dev.crm.entities.Task;
import com.dev.crm.entities.User;
import com.dev.crm.repositories.RoleRepository;
import com.dev.crm.repositories.TaskRepository;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;

    public UserService(
        UserRepository userRepository,
        BCryptPasswordEncoder passwordEncoder,
        RoleRepository roleRepository,
        TaskRepository taskRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> search(String search) {
        return userRepository.search("%" + search + "%");
    }

    @Override
    public Long update(UpdateUserCommand updateUserCommand) {
        User user = userRepository.findById(updateUserCommand.id())
        .orElseThrow(() -> {
            throw new ResourceNotFoundException("Не найден пользователь с ID: " + updateUserCommand.id());
        });

        if (updateUserCommand.login() != null) {
            user.setLogin(updateUserCommand.login());
        }

        if (updateUserCommand.password() != null) {
            user.setPassword(passwordEncoder.encode(updateUserCommand.password()));
        }

        if (updateUserCommand.firstName() != null) {
            user.setFirstName(updateUserCommand.firstName());
        }

        if (updateUserCommand.lastName() != null) {
            user.setLastName(updateUserCommand.lastName());
        }

        if (updateUserCommand.roleId() != null) {
            Role role = roleRepository.findById(updateUserCommand.roleId()).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Не найдена роль с ID: " + updateUserCommand.roleId());
                }
            );

            user.setRole(role);
        }

        if (updateUserCommand.phone() != null) {
            user.setPhone(updateUserCommand.phone());
        }

        if (updateUserCommand.taskIds() != null) {
            Set<Task> tasks = taskRepository.findAllById(updateUserCommand.taskIds()).stream().collect(Collectors.toSet());
            user.setTasks(tasks);
        }

        userRepository.save(user);

        return user.getId();
    }

    @Override
    public Long create(CreateUserCommand createUserCommand) {
        User user = new User();
        user.setLogin(createUserCommand.login());
        user.setPassword(passwordEncoder.encode(createUserCommand.password()));
        user.setFirstName(createUserCommand.firstName());
        user.setLastName(createUserCommand.lastName());

        Role role = roleRepository.findById(createUserCommand.roleId()).orElseThrow(
            () -> {
                throw new ResourceNotFoundException("Не найдена роль с ID: " + createUserCommand.roleId());
            }
        );

        user.setRole(role);
        user.setPhone(createUserCommand.phone());

        if (createUserCommand.taskIds() != null && !createUserCommand.taskIds().isEmpty()) {
            Set<Task> tasks = taskRepository.findAllById(createUserCommand.taskIds()).stream().collect(Collectors.toSet());
            user.setTasks(tasks);
        }

        Long insertId = userRepository.save(user).getId();
        user.setId(insertId);

        return insertId;
    }

    @Override
    public Long delete(Long userId) {
        userRepository.deleteById(userId);

        return userId;
    }
}
