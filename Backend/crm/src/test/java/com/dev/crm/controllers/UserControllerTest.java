package com.dev.crm.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dev.crm.dto.command.user.CreateUserCommand;
import com.dev.crm.dto.request.user.CreateUserRequest;
import com.dev.crm.dto.request.user.SearchUsersRequest;
import com.dev.crm.entities.Role;
import com.dev.crm.entities.User;
import com.dev.crm.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
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
            "+7 (999) 123-45-67", 
            new HashSet<>()
        );
    }

    private static User userWithBadFieldsBuilder() {
        return new User(
            1L, 
            "1", 
            "1", 
            "1", 
            "1", 
            new Role(1L, "system name", "display name", new HashSet<>()), 
            "", 
            new HashSet<>()
        );
    }

    private static CreateUserRequest createUserRequestBuilder() {
        return new CreateUserRequest(
            "Login",
            "Password",
            "First Name",
            "Last Name",
            1L,
            "+7 971 057 89 01",
            List.of()
        );
    }

    @Test
    @WithMockUser(username="John", roles={"USER"})
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
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").value(hasSize(3)))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[2].id").value(3));
    }

    @Test
    @WithMockUser(username="John", roles={"USER"})
    @DisplayName("GET /api/users возвращает 200 и пустой список")
    void shouldReturnEmptyUsersList_WhenIsEmpty() throws Exception {
        Mockito.when(userService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").value(hasSize(0)));
    }

    @Test
    @WithMockUser(username="John", roles={"USER"})
    @DisplayName("GET /api/users/search возвращает 200 и список найденных пользователей")
    void shouldReturnSearchedUsers_WhenHasSearched() throws Exception {
        User user1 = userBuilder(1L);
        User user2 = userBuilder(2L);
        User user3 = userBuilder(3L);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        SearchUsersRequest request = new SearchUsersRequest("login");

        Mockito.when(userService.search("login")).thenReturn(users);

        mockMvc.perform(get("/api/users/search").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").value(hasSize(3)))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[2].id").value(3));

        Mockito.verify(userService, Mockito.times(1)).search("login");
    }

    @Test
    @WithMockUser(username="John", roles={"USER"})
    @DisplayName("GET /api/users/search возвращает 200 и пустой список пользователей")
    void shouldReturnEmptySearchedUsersList_WhenNotSearched() throws Exception {
        Mockito.when(userService.search("search")).thenReturn(List.of());

        SearchUsersRequest request = new SearchUsersRequest("search");

        mockMvc.perform(get("/api/users/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").value(hasSize(0)));

        Mockito.verify(userService, Mockito.times(1)).search("search");
    }

    @Test
    @WithMockUser(username="John", roles={"USER"})
    @DisplayName("GET /api/users/search возвращает 400 и сообщение об ошибке валидации")
    void shoudReturn400_WhenHasNotSearchParam() throws Exception {
        SearchUsersRequest request = new SearchUsersRequest(null);

        mockMvc.perform(get("/api/users/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "John", roles={"USER"})
    @DisplayName("GET /api/users/{id} возвращает 200 и найденного пользователя")
    void shouldReturnUserData_WhenHasUser() throws Exception {
        User user = userBuilder(1L);

        Mockito.when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.login").value("login"));

        Mockito.verify(userService, Mockito.times(1)).findById(1L);
    }

    @Test
    @WithMockUser(username = "John", roles={"USER"})
    @DisplayName("GET /api/users/{id} возвращает 404 и пользователь не найден")
    void shouldReturnNoUserData_WhenHasNotUser() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Mockito.verify(userService, Mockito.times(1)).findById(1L);
    }

    @Test
    @WithMockUser(username = "John", roles={"ADMIN"})
    @DisplayName("POST /api/users/create возвращает 201 и ID созданного пользователя")
    void shouldReturnAddedUserId_WhenSuccess() throws Exception {
        CreateUserRequest request = createUserRequestBuilder();
        
        Mockito.when(userService.create(Mockito.any(CreateUserCommand.class))).thenReturn(1L);

        mockMvc.perform(
                    post("/api/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("1"));

        Mockito.verify(userService, Mockito.times(1)).create(Mockito.any(CreateUserCommand.class));
    }

    // @Test
    // @DisplayName("POST /api/users/create возвращает 400 и список ошибок в полях")
    // void shouldBadRequest_WhenNotValidateUserFields() throws Exception {
    //     User user = userWithBadFieldsBuilder();

    //     mockMvc.perform(
    //         post("/api/users/create")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(objectMapper.writeValueAsString(user))
    //     )
    //     .andExpect(status().isBadRequest())
    //     // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //     .andExpect(jsonPath("$.message").exists());
    // }

    // @Test
    // @DisplayName("POST /api/users/update возвращает 200 и ID обновленного пользователя")
    // void shouldReturnUpdatedUserId_WhenSuccess() throws Exception {
    //     User user = userBuilder(1L);
        
    //     Mockito.when(userService.update(Mockito.any(User.class))).thenReturn(1L);

    //     mockMvc.perform(
    //                 post("/api/users/update")
    //                 .contentType(MediaType.APPLICATION_JSON)
    //                 .content(objectMapper.writeValueAsString(user))
    //             )
    //             .andExpect(status().isOk())
    //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(content().string("1"));

    //     Mockito.verify(userService, Mockito.times(1)).update(Mockito.any(User.class));
    // }

    // @Test
    // @DisplayName("DELETE /api/users/{id} возвращает 200 и ID удаленного пользователя")
    // void shouldReturnDeletedUserId_WhenSuccess() throws Exception {
    //     Mockito.when(userService.delete(1L)).thenReturn(1L);

    //     mockMvc.perform(delete("/api/users/1"))
    //             .andExpect(status().isOk())
    //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(content().string("1"));

    //     Mockito.verify(userService, Mockito.times(1)).delete(1L);
    // }

}
