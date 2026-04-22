package com.dev.crm.services.users;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.User;
import com.dev.crm.repositories.UserRepository;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public List<User> search(String search) {
        return this.userRepository.search(search);
    }

    @Override
    public Long update(User user) {
        return this.userRepository.save(user).getId();
    }

    @Override
    public Long create(User user) {
        Long insertId = this.userRepository.save(user).getId();
        user.setId(insertId);
        return insertId;
    }

    @Override
    public Long delete(Long userId) {
        this.userRepository.deleteById(userId);

        return userId;
    }
}
