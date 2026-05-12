package com.dev.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.crm.contracts.services.UserServiceContract;
import com.dev.crm.entities.User;
import com.dev.crm.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService, UserServiceContract {
    private final UserRepository userRepository;

    public UserService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
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
    public Long delete(Long userId) {
        userRepository.deleteById(userId);

        return userId;
    }

    @Override
    public Boolean existsByLogin(String login) {
        User user = userRepository.findByLogin(login).orElse(null);

        return user != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Не найден пользователь с логином: " + username);
            }
        );

        return user;
    }
}
