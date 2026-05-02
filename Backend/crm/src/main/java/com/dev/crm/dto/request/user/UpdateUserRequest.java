package com.dev.crm.dto.request.user;

import java.util.List;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Long roleId;
    private String phone;
    private List<Long> taskIds;
}
