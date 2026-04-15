package com.dev.crm.entities;

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
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="login", nullable=false, length=255)
    private String login;

    @Column(name="password", nullable=false, length=2048)
    private String password;

    @Column(name="first_name", nullable=false, length=255)
    private String firstName;

    @Column(name="last_name", nullable=false, length=255)
    private String lastName;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="role", nullable=false)
    private Role role;

    @Column(name="phone", nullable=false, length=45)
    private String phone;

    @ManyToMany
    @JoinTable(
        name="users_tasks_map",
        joinColumns=@JoinColumn(name="user_id", nullable=false),
        inverseJoinColumns=@JoinColumn(name="task_id", nullable=false)
    )
    private Set<Task> tasks = new HashSet<>();
}
