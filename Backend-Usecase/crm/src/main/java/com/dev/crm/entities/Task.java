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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="task")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false, length=255)
    private String title;

    @Column(name="price", nullable=false)
    private Long price;

    @Column(name="deadline", nullable=false)
    private LocalDateTime deadline;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="status", nullable=false)
    private TaskStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="responsible", nullable=false)
    private User responsible;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client", nullable=true)
    private User client;

    @Column(name="is_editing", nullable=false, columnDefinition="TINYINT(1)")
    private Boolean isEditing;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="tasks_attachments",
        joinColumns=@JoinColumn(name="task_id", nullable=false),
        inverseJoinColumns=@JoinColumn(name="attachment_id", nullable=false)
    )
    private Set<Attachment> attachments = new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="users_tasks_map",
        joinColumns=@JoinColumn(name="task_id", nullable=false),
        inverseJoinColumns=@JoinColumn(name="user_id", nullable=false)
    )
    private Set<User> assignees;
}
