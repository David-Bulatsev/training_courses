package com.lms_system.training_courses.test_controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms_system.training_courses.entity.Role;
import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.service.UserService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenSaveValidUser_thenReturnUser() throws Exception {
        User user = new User();
        user.setNickname("Vladik");
        user.setPassword("Vlad123@");
        user.setFullname("FUllname");
        user.setEmail("ABcd@gmail.cim");
        user.setRole(Role.STUDENT);
        user.setId(7L);
        user.setCreatedAt(LocalDateTime.now());

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("Vladik"));
    }

    @Test
    public void whenUpdateUser_thenCheckUpdatedAt() throws Exception {
        User updatedUser = new User();
        updatedUser.setNickname("updatedNick");
        updatedUser.setEmail("updated@example.com");

        given(userService.updateUser(any(User.class), eq(1L))).willAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setUpdatedAt(LocalDateTime.now()); // Эмулируем обновление времени
            return user;
        });


        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updatedAt").isNotEmpty()); // Проверяем, что updatedAt заполнен
    }

}
