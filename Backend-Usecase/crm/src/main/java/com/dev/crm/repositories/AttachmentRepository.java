package com.dev.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crm.entities.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    
}
