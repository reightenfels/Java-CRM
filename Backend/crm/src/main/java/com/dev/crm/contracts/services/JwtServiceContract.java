package com.dev.crm.contracts.services;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;

import com.dev.crm.entities.User;

import io.jsonwebtoken.Claims;

public interface JwtServiceContract {
    /**
     * Возвращает ключ подписи JWT
     * @return - ключ
     */
    public SecretKey getSigninKey();

    /**
     * Генерирует JWT токен
     * @param user - сущность пользователя
     * @param expiryTime - время жизни
     * @return - токен
     */
    public String generateToken(User user, long expiryTime);

    /**
     * Генерирует JWT access token
     * @param user - сущность пользователя
     * @return - access token
     */
    public String generateAccessToken(User user);

    /**
     * Генерирует JWT refresh token
     * @param user - сущность пользователя
     * @return - refresh token
     */
    public String generateRefreshToken(User user);

    /**
     * Получает ВСЕ данные из токена
     * @param token - токен
     * @return - словарь данных токена
     */
    public Claims extractAllClaims(String token);

    /**
     * Возвращает нужные данные из токена
     * @param <T> - параметр нужных данных
     * @param token - токен
     * @param resolver - функция callback
     * @return - нужные данные
     */
    public <T> T extractClaim(String token, Function<Claims, T> resolver);

    /**
     * Возвращает username из токена
     * @param token - токен
     * @return - username
     */
    public String extractUsername(String token);

    /**
     * Возвращает дату истечения токена
     * @param token - токен
     * @return - дата истечения
     */
    public Date extractExpiration(String token);

    /**
     * Проверяет истекло ли время жизни токена
     * @param token - токен
     * @return - Да\нет
     */
    public boolean isAccessTokenExpired(String token);

    /**
     * Проверяет валидность токена
     * @param token - токен
     * @param user - сущность авторизуемого пользователя
     * @return - Да\нет
     */
    public boolean isValid(String token, UserDetails user);
}
