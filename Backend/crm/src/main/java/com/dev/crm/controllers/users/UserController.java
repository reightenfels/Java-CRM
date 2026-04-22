package com.dev.crm.controllers.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.entities.User;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;
import com.dev.crm.services.users.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userSerivice;

    public UserController(UserService userSerivice) {
        this.userSerivice = userSerivice;
    }

    @GetMapping
    public List<User> findAll() {
        return this.userSerivice.findAll();
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String search) {
        return this.userSerivice.search(search);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userSerivice.findById(id).orElseThrow(
            () -> {
                throw new ResourceNotFoundException("Не найден пользователь с ID: " + id);
            }
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Long create(@RequestBody User user) {
        return this.userSerivice.create(user);
    }

    @PostMapping("/update")
    public Long update(@RequestBody User user) {
        return this.userSerivice.update(user);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.userSerivice.delete(id);
    }
}
