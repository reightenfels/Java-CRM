package com.dev.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.ClientDialogMessage;

public interface ClientDialogMessagesRepository extends JpaRepository<ClientDialogMessage, Long> {
    public List<ClientDialogMessage> findByDialogId(Long dialogId);
}
