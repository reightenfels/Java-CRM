package com.dev.crm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crm.entities.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    public Optional<Token> findByAccessToken(String accessToken);
    public Optional<Token> findByRefreshToken(String refreshToken);

    @Query(
        value="""
            SELECT 
                t.* 
            FROM auth_token t 
            JOIN user u ON t.user_id = u.id 
            WHERE 
                u.id = ?1 
                AND 
                t.is_logged_out = 0
            """, 
        nativeQuery=true)
    public List<Token> findAllAccessTokenByUser(Long userId);
}