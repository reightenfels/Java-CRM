package com.dev.crm.dto.request.user;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequest {
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

    @NotBlank(message = "Роль обязательна")
    private Long roleId;

    @NotBlank(message = "Телефон обязателен")
    private String phone;

    private List<Long> taskIds;
}
