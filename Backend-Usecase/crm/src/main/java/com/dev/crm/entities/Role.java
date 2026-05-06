package com.dev.crm.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(name="role")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="system_name", nullable=false, length=255)
    private String systemName;

    @Column(name="display_name", nullable=false, length=255)
    private String displayName;

    @Column(name="level", nullable=false)
    private int level;

    @ManyToMany
    @JoinTable(
        name="role_privilegy_map",
        joinColumns=@JoinColumn(name="role", nullable=false),
        inverseJoinColumns=@JoinColumn(name="privilegy", nullable=false)
    )
    private Set<Privilegy> privileges = new HashSet<>();

    @Override
    public String getAuthority() {
        return systemName;
    }
}