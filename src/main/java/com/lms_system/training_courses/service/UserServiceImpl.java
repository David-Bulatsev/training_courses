package com.lms_system.training_courses.service;

import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    // save operation
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // read operation
    @Override
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    // updateOperation
    @Override
    public User updateUser(User user, Long userId) {
        User userDB = userRepository.findById(userId).get();

        userDB.setNickname(user.getNickname());

        return userRepository.save(userDB);
    }

    // deleteOperation
    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
