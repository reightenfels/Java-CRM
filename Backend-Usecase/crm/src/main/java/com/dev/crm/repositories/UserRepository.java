package com.dev.crm.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.crm.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE login LIKE ?1", nativeQuery = true)
    public List<User> search(String search);

    public Optional<User> findByLogin(String login);

    public List<User> findByLoginOrPhoneOrEmail(String login, String phone, String email);

    @Query(value="""
            SELECT
                u.login
            FROM
                user u
            JOIN
                role r
            ON
                u.role = r.id
            WHERE
                r.system_name = 'ROLE_MANAGER'
            """, nativeQuery=true)
    public List<String> findAllManagers();

    @Query(value="""
            SELECT
                u.login
            FROM
                user u
            JOIN
                role r
            ON
                u.role = r.id
            WHERE
                r.system_name = 'ROLE_EMPLOYEE'
            """, nativeQuery=true)
    public List<String> findAllEmployees();

    @Query(value="""
            SELECT
                u.login
            FROM
                user u
            JOIN
                role r
            ON
                u.role = r.id
            WHERE
                r.system_name = 'ROLE_CLIENT'
            """, nativeQuery=true)
    public Set<String> findAllClients();

    @Query(value="""
            SELECT
                *
            FROM
                user
            WHERE
                login in :logins
            """, nativeQuery=true)
    public List<User> findAllByLogin(@Param("logins") Set<String> logins);
}