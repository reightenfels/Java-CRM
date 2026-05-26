package com.dev.crm.services;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.crm.contracts.services.AuthServiceContract;
import com.dev.crm.dto.command.auth.LoginUserCommand;
import com.dev.crm.dto.command.auth.RegisterUserCommand;
import com.dev.crm.dto.response.auth.AuthResponse;
import com.dev.crm.entities.Role;
import com.dev.crm.entities.Token;
import com.dev.crm.entities.User;
import com.dev.crm.repositories.RoleRepository;
import com.dev.crm.repositories.TokenRepository;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.CustomAuthException;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService implements AuthServiceContract {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository,
                                 JwtService jwtService,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 TokenRepository tokenRepository,
                                 RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(RegisterUserCommand command) {
        User user = new User();

        user.setLogin(command.login());
        user.setPassword(passwordEncoder.encode(command.password()));
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setPhone(command.phone());
        user.setEmail(command.email());
        
        Role role = roleRepository.findById(command.roleId()).orElseThrow(
            () -> {
                throw new ResourceNotFoundException("Не найдена роль с ID: " + command.roleId());
            }
        );

        user.setRole(role);

        userRepository.save(user);
    }

    @Override
    public void revokeAllToken(User user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokenByUser(user.getId());

        if(!validTokens.isEmpty()){
            validTokens.forEach(t ->{
                t.setIsLoggedOut(true);
            });
        }
        
        tokenRepository.saveAll(validTokens);
    }

    @Override
    public void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setIsLoggedOut(false);
        token.setUser(user);
        
        tokenRepository.save(token);
    }

    @Override
    public AuthResponse authenticate(LoginUserCommand command) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                command.login(),
                command.password()
            )
        );

        User user = userRepository.findByLogin(command.login()).orElseThrow(
            () -> new UsernameNotFoundException("Не найден пользователь с логином: " + command.login())
        );

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new CustomAuthException("Не найден токен авторизации в заголовке");
        }
        
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByLogin(username).orElseThrow(
            () -> new ResourceNotFoundException("Не найден пользователь с логином: " + username)
        );

        if (jwtService.isValidRefresh(token, user)) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllToken(user);

            saveUserToken(accessToken, refreshToken, user);

            return new AuthResponse(accessToken, refreshToken);
        }

        throw new CustomAuthException("Невалидный токен");
    }
}
