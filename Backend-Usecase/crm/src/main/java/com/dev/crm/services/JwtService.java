package com.dev.crm.services;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dev.crm.contracts.services.JwtServiceContract;
import com.dev.crm.entities.User;
import com.dev.crm.repositories.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements JwtServiceContract {
    @Value("${security.jwt.secret_key}")
    private String secretKey;

    @Value("${security.jwt.access_token_expiration}")
    private long accessTokenExpiration;

    @Value("${security.jwt.refresh_token_expiration}")
    private long refreshTokenExpiration;
    
    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
  
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(User user, long expiryTime) {
        JwtBuilder builder = Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiryTime))
                .signWith(getSigninKey());

        return builder.compact();
    }

    @Override
    public String generateAccessToken(User user) {
        return generateToken(user, accessTokenExpiration);
    }

    @Override
    public String generateRefreshToken(User user) {
        return generateToken(user, refreshTokenExpiration);
    }

    @Override
    public Claims extractAllClaims(String token) {
        JwtParserBuilder parser = Jwts.parser();
        parser.verifyWith(getSigninKey());

        return parser.build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
  
        return resolver.apply(claims);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public boolean isAccessTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        boolean isValidToken = tokenRepository.findByAccessToken(token).map(t -> !t.getIsLoggedOut()).orElse(false);

        return username.equals(user.getUsername())
                && !isAccessTokenExpired(token)
                && isValidToken;
    }

    @Override
    public boolean isValidRefresh(String token, User user) {
        String username = extractUsername(token);
  
        boolean isValidRefreshToken = tokenRepository.findByRefreshToken(token).map(t -> !t.getIsLoggedOut()).orElse(false);

        return username.equals(user.getUsername())
                && isAccessTokenExpired(token)
                && isValidRefreshToken;
    }

    
}
