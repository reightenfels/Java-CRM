package com.dev.crm.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Отсутствует логин в запросе")
    @Size(min = 2, max= 255, message = "Логин от 2 до 255 символов")
    private String Login;

    @NotBlank(message = "Отсутствует пароль в запросе")
    @Size(min = 2, max = 2048, message = "Логин от 2 до 2048 символов")
    private String password;
}
