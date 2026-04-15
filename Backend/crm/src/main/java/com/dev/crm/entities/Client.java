package com.dev.crm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="login", nullable=false, length=255)
    private String login;

    @Column(name="email", nullable=false, length=255)
    private String email;

    @Column(name="phone", nullable=false, length=255)
    private String phone;

    @Column(name="is_editing", nullable=false, columnDefinition = "TINYINT(1)")
    private Boolean isEditing;

    @Column(name="first_name", nullable=false, length=255)
    private String firstName;

    @Column(name="last_name", nullable=false, length=255)
    private String lastName;
}
