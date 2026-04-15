package com.dev.crm.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="task_dialog_message")
@Getter
@Setter
public class TaskDialogMessage {
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
    private TaskDialog dialog;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
}
