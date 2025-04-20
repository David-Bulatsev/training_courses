package com.lms_system.training_courses.service;

import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.repository.UserRepository;
import com.lms_system.training_courses.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;  // Мок репозитория

    @InjectMocks
    private UserServiceImpl userService;  // Тестируемый сервис

    private User createTestUser(Long id, String nickname) {
        User user = new User();
        user.setId(id);
        user.setNickname(nickname);
        user.setEmail(nickname + "@example.com");
        user.setPassword("password123");
        return user;
    }

    @Test
    void whenSaveUser_thenReturnSavedUser() {
        User testUser = createTestUser(1L, "testUser");

        when(userRepository.save(testUser)).thenReturn(testUser);

        User savedUser = userService.saveUser(testUser);

        assertEquals("testUser", savedUser.getNickname());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void whenFetchAllUsers_thenReturnUserList() {
        User user1 = createTestUser(1L, "user1");
        User user2 = createTestUser(2L, "user2");
        List<User> users = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.fetchUserList();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void whenUpdateUser_thenReturnUpdatedUser() {
        User existingUser = createTestUser(1L, "oldNick");
        User newData = createTestUser(null, "newNick");  // ID не меняется

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User updatedUser = userService.updateUser(newData, 1L);

        assertEquals("newNick", updatedUser.getNickname());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void whenUpdateNonExistentUser_thenThrowException() {
        User newData = createTestUser(null, "newNick");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUser(newData, 1L));

        verify(userRepository, never()).save(any());
    }

    @Test
    void whenDeleteUser_thenVerifyRepositoryCall() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void whenSaveNullUser_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(null));
    }

    @Test
    void whenUpdateUserEmail_thenEmailIsChanged() {
        User existingUser = createTestUser(1L, "user1");
        existingUser.setUpdatedAt(null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setUpdatedAt(LocalDateTime.now());
            return user;
        });

        User updatedUser = userService.updateUser(createTestUser(1L, "updated"), 1L);
        assertNotNull(updatedUser.getUpdatedAt());
    }
}