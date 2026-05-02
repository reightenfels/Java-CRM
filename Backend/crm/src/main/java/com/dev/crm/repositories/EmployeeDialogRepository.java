package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.PersonalEmployeeDialog;

public interface EmployeeDialogRepository extends JpaRepository<PersonalEmployeeDialog, Long> {
    public List<PersonalEmployeeDialog> findByCompanionLogin(String login);
}
