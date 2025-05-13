package com.lms_system.training_courses.service.impl;

import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.repository.UserRepository;
import com.lms_system.training_courses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long userId) {
        User userDB = userRepository.findById(userId).get();
        if (user.getName() != null)
            userDB.setName(user.getName());
        if (user.getPassword() != null)
            userDB.setPassword(user.getPassword());
        if (user.getEmail() != null)
            userDB.setEmail(user.getEmail());
        userDB.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(userDB);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name).get();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
