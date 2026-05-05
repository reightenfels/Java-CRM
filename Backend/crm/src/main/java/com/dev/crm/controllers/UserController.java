package com.dev.crm.controllers;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.contracts.services.UserServiceContract;
import com.dev.crm.dto.command.user.UpdateUserCommand;
import com.dev.crm.dto.request.user.SearchUsersRequest;
import com.dev.crm.dto.request.user.UpdateUserRequest;
import com.dev.crm.dto.response.ApiResponse;
import com.dev.crm.entities.User;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    private final UserServiceContract userSerivice;

    public UserController(UserServiceContract userSerivice) {
        this.userSerivice = userSerivice;
    }

    @GetMapping
    public ApiResponse<List<User>> findAll() {
        List<User> users = this.userSerivice.findAll();

        return ApiResponse.ok(users);
    }

    @GetMapping("/search")
    public ApiResponse<List<User>> search(@RequestBody @Valid SearchUsersRequest request) {
        List<User> users = this.userSerivice.search(request.getSearch());

        return ApiResponse.ok(users);
    }

    @GetMapping("/{id}")
    public ApiResponse<User> findById(@PathVariable Long id) {
        User user = this.userSerivice.findById(id).orElseThrow(
            () -> {
                throw new ResourceNotFoundException("Не найден пользователь с ID: " + id);
            }
        );

        return ApiResponse.ok(user);
    }

    @PostMapping("/update")
    public ApiResponse<Long> update(@RequestBody @Valid UpdateUserRequest request) {
        UpdateUserCommand command = new UpdateUserCommand(
            request.getId(),
            request.getLogin(),
            request.getPassword(),
            request.getFirstName(),
            request.getLastName(),
            request.getRoleId(),
            request.getPhone(),
            request.getTaskIds()
        );

        Long userId = this.userSerivice.update(command);

        return ApiResponse.ok(userId);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Long> delete(@PathVariable Long id) {
        Long userId = this.userSerivice.delete(id);

        return ApiResponse.ok(userId);
    }
}
