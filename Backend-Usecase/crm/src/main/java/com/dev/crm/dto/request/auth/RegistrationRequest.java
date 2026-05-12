package com.dev.crm.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Логин обязателен")
    @Size(min = 2, max= 255, message = "Логин от 2 до 255 символов")
    private String login;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 2, max = 2048, message = "Логин от 2 до 2048 символов")
    private String password;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 255, message = "Имя от 2 до 255 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 255, message = "Фамилия от 2 до 255 символов")
    private String lastName;

    @NotBlank(message = "Телефон обязателен")
    private String phone;

    @NotBlank(message = "Email обязателен")
    private String email;
}
