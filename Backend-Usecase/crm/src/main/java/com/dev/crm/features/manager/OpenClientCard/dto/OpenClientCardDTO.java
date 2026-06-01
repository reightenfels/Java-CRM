package com.dev.crm.features.manager.OpenClientCard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenClientCardDTO {
    private String login;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
