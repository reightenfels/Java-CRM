package com.dev.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import com.dev.crm.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE login LIKE ?1", nativeQuery = true)
    public List<User> search(String search);

    public Optional<User> findByLogin(String login);
}