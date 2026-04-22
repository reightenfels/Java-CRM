package com.dev.crm.controllers.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dev.crm.entities.Role;
import com.dev.crm.entities.User;
import com.dev.crm.services.users.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private static User userBuilder (Long id) {
        return new User(
            id, 
            "login", 
            UUID.randomUUID().toString(), 
            "first name", 
            "last name", 
            new Role(1L, "system name", "display name", new HashSet<>()), 
            "phone", 
            new HashSet<>());
    }

    @Test
    @DisplayName("GET /api/users возвращает 200 и список пользователей")
    void shouldReturnAllUsers_WhenNotEmpty() throws Exception {
        User user1 = userBuilder(1L);
        User user2 = userBuilder(2L);
        User user3 = userBuilder(3L);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(3)))
                .andExpect(jsonPath("$[0].login").value("login"))
                .andExpect(jsonPath("$[1].login").value("login"))
                .andExpect(jsonPath("$[2].login").value("login"));
    }

    @Test
    @DisplayName("GET /api/users возвращает 200 и пустой список")
    void shouldReturnEmptyUsersList_WhenIsEmpty() throws Exception {
        Mockito.when(userService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(0)));
    }

    @Test
    @DisplayName("GET /api/users/search возвращает 200 и список найденных пользователе")
    void shouldReturnSearchedUsers_WhenHasSearched() throws Exception {
        User user1 = userBuilder(1L);
        User user2 = userBuilder(2L);
        User user3 = userBuilder(3L);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Mockito.when(userService.search("login")).thenReturn(users);

        mockMvc.perform(get("/api/users/search").param("search", "login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(3)))
                .andExpect(jsonPath("$[0].login").value("login"))
                .andExpect(jsonPath("$[1].login").value("login"))
                .andExpect(jsonPath("$[2].login").value("login"));

        Mockito.verify(userService, Mockito.times(1)).search("login");
    }

    @Test
    @DisplayName("GET /api/users/search возвращает 200 и пустой список пользователей")
    void shouldReturnEmptySearchedUsersList_WhenNotSearched() throws Exception {
        Mockito.when(userService.search("search")).thenReturn(List.of());

        mockMvc.perform(get("/api/users/search").param("search", "search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(0)));

        Mockito.verify(userService, Mockito.times(1)).search("search");
    }

    @Test
    @DisplayName("GET /api/users/{id} возвращает 200 и найденного пользователя")
    void shouldReturnUserData_WhenHasUser() throws Exception {
        User user = userBuilder(1L);

        Mockito.when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.login").value("login"));

        Mockito.verify(userService, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("GET /api/users/{id} возвращает 404 и пользователь не найден")
    void shouldReturnNoUserData_WhenHasNotUser() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());

        Mockito.verify(userService, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("POST /api/users/create возвращает 201 и ID созданного пользователя")
    void shouldReturnAddedUserId_WhenSuccess() throws Exception {
        User user = userBuilder(1L);
        
        Mockito.when(userService.create(Mockito.any(User.class))).thenReturn(1L);

        mockMvc.perform(
                    post("/api/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("1"));

        Mockito.verify(userService, Mockito.times(1)).create(Mockito.any(User.class));
    }

    @Test
    @DisplayName("POST /api/users/update возвращает 200 и ID обновленного пользователя")
    void shouldReturnUpdatedUserId_WhenSuccess() throws Exception {
        User user = userBuilder(1L);
        
        Mockito.when(userService.update(Mockito.any(User.class))).thenReturn(1L);

        mockMvc.perform(
                    post("/api/users/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("1"));

        Mockito.verify(userService, Mockito.times(1)).update(Mockito.any(User.class));
    }

    @Test
    @DisplayName("DELETE /api/users/{id} возвращает 200 и ID удаленного пользователя")
    void shouldReturnDeletedUserId_WhenSuccess() throws Exception {
        Mockito.when(userService.delete(1L)).thenReturn(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("1"));

        Mockito.verify(userService, Mockito.times(1)).delete(1L);
    }
}
