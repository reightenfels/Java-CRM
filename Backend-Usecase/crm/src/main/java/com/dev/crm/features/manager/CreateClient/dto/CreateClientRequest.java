package com.dev.crm.features.manager.CreateClient.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateClientRequest {
    @NotBlank(message = "Отсутствует логин в запросе")
    private String login;

    @NotBlank(message = "Отсутствует пароль в запросе")
    private String password;

    @NotBlank(message = "Отсутствует имя в запросе")
    private String firstName;

    @NotBlank(message = "Отсутствует фамилия в запросе")
    private String lastName;

    @NotBlank(message = "Отсутствует телефон в запросе")
    private String phone;

    @NotBlank(message = "Отсутствует почта в запросе")
    private String email;
}
