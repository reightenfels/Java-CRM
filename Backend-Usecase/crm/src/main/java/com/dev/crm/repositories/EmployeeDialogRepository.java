package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.crm.entities.PersonalEmployeeDialog;

public interface EmployeeDialogRepository extends JpaRepository<PersonalEmployeeDialog, Long> {
    @Query(value = """
            SELECT
                *
            FROM personal_employee_dialog
            WHERE
                companion_1 = :userId
                OR
                companion_2 = :userId
            """, nativeQuery = true)
    public List<PersonalEmployeeDialog> findAllByUser(@Param("userId") Long userId);
}
