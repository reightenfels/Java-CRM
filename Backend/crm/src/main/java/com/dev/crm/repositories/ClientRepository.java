package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dev.crm.entities.Client;

import jakarta.transaction.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT * FROM clients WHERE login LIKE ?1", nativeQuery = true)
    public List<Client> search(String search);

    @Modifying
    @Transactional
    @Query(value = "UPDATE clients SET is_editing = 1 WHERE id = ?1", nativeQuery = true)
    public int makeEditingOn(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE clients SET is_editing = 0 WHERE id = ?1", nativeQuery = true)
    public int makeEditingOff(Long id);
}
