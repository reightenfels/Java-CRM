package com.dev.crm.dto.command.user;

import java.util.List;

public record UpdateUserCommand(
    Long id,
    String login,
    String password,
    String firstName,
    String lastName,
    Long roleId,
    String phone,
    List<Long> taskIds
) {
    
}
