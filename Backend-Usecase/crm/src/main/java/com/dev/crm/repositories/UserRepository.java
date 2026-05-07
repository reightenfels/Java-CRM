package com.dev.crm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crm.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE login LIKE ?1", nativeQuery = true)
    public List<User> search(String search);

    public Optional<User> findByLogin(String login);

    public List<User> findByLoginOrPhoneOrEmail(String login, String phone, String email);
}