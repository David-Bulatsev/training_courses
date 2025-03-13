package com.lms_system.training_courses.service;

import com.lms_system.training_courses.entity.User;

import java.util.List;

public interface UserService {
    // save operation
    User saveUser(User user);

    // read operation
    List<User> fetchUserList();

    // update operation
    User updateUser(User user, Long UserId);

    // delete operation
    void deleteUserById(Long userId);
}
