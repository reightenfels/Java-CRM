package com.dev.crm.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@Getter
@Setter
public class User implements UserDetails {
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

    @Column(name="email", nullable=false, length=255)
    private String email;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="users_tasks_map",
        joinColumns=@JoinColumn(name="user_id", nullable=false),
        inverseJoinColumns=@JoinColumn(name="task_id", nullable=false)
    )
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private List<Token> tokens;

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
