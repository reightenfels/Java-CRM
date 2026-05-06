package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.crm.entities.PersonalEmployeeDialogMessage;

public interface EmployeeDialogMessagesRepository extends JpaRepository<PersonalEmployeeDialogMessage, Long> {
    public Long deleteByDialogId(Long dialogId);

    public List<PersonalEmployeeDialogMessage> findByDialogId(Long dialogId);

    @Query(value = """
            SELECT 
                m.*
            FROM 
                personal_employee_dialog_message m
            JOIN 
                personal_employee_dialog d ON m.dialog_id = d.id
            WHERE
                (d.companion_1 = :user1 AND d.companion_2 = :user2)
                OR
                (d.companion_1 = :user2 AND d.companion_2 = :user1)
            ORDER BY
                m.created_at ASC
            """, nativeQuery = true)
    public List<PersonalEmployeeDialogMessage> findByCompanions(@Param("user1") Long user1, @Param("user2") Long user2);
}
