package com.lms_system.training_courses.service;

import com.lms_system.training_courses.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User saveUser(User user);

    List<User> fetchUserList();

    User updateUser(User user, Long UserId);

    void deleteUserById(Long userId);

    User getUserByName(String name);
}
