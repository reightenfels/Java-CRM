package com.dev.crm.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="personal_employee_dialog_message")
@Getter
@Setter
public class PersonalEmployeeDialogMessage {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="text", nullable=false)
    private String text;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sender_id", nullable=false)
    private User sender;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dialog_id", nullable=false)
    private PersonalEmployeeDialog dialog;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name="personal_employee_dialog_messages_attachments",
        joinColumns=@JoinColumn(name="message_id", nullable=false),
        inverseJoinColumns=@JoinColumn(name="attachment_id", nullable=false)
    )
    private Set<Attachment> attachments = new HashSet<>();
}
