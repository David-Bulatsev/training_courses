package com.lms_system.training_courses.service.impl;

import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.repository.UserRepository;
import com.lms_system.training_courses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // save operation
    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
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
        userDB.setName(user.getName());
        return userRepository.save(userDB);
    }

    // deleteOperation
    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
