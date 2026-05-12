package com.dev.crm.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.contracts.services.AuthServiceContract;
import com.dev.crm.contracts.services.RoleServiceContract;
import com.dev.crm.contracts.services.UserServiceContract;
import com.dev.crm.dto.command.auth.LoginUserCommand;
import com.dev.crm.dto.command.auth.RegisterUserCommand;
import com.dev.crm.dto.request.auth.LoginRequest;
import com.dev.crm.dto.request.auth.RegistrationRequest;
import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.dto.response.auth.AuthResponse;
import com.dev.crm.entities.Role;
import com.dev.crm.resterrorconfig.exceptions.LoginIsAlreadyTakenException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceContract authService;
    private final UserServiceContract userService;
    private final RoleServiceContract roleService;

    public AuthController(
        AuthServiceContract authService,
        UserServiceContract userService,
        RoleServiceContract roleService
    ) {
        this.authService = authService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/registration")
    public ApiResponse<AuthResponse> registration(@RequestBody @Valid RegistrationRequest request) {
        if(userService.existsByLogin(request.getLogin())) {
            throw new LoginIsAlreadyTakenException("Логин уже занят");
        }

        Role role = roleService.findByMinLevel();

        RegisterUserCommand registrationCommand = new RegisterUserCommand(
            request.getLogin(),
            request.getPassword(),
            request.getFirstName(),
            request.getLastName(),
            request.getPhone(),
            role.getId(),
            request.getEmail()
        );

        authService.register(registrationCommand);

        LoginUserCommand loginUserCommand = new LoginUserCommand(
            request.getLogin(),
            request.getPassword()
        );

        return ApiResponse.ok(authService.authenticate(loginUserCommand));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginUserCommand loginUserCommand = new LoginUserCommand(
            request.getLogin(),
            request.getPassword()
        );

        return ApiResponse.ok(authService.authenticate(loginUserCommand));
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthResponse> refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        return ApiResponse.ok(authService.refreshToken(request, response));
    }
}
