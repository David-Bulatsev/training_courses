package com.lms_system.training_courses.controller;

import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired private UserService userService;

    // save operation
    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    // read operation
    @GetMapping("/users")
    public List<User> fetchUserList() {
        return userService.fetchUserList();
    }

    // update operation
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        return userService.updateUser(user, userId);
    }

    // delete operation
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
        return "Deleted Successfully";
    }
}
