package com.dev.crm.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Отсутствует логин в запросе")
    private String Login;

    @NotBlank(message = "Отсутствует пароль в запросе")
    private String password;
}
