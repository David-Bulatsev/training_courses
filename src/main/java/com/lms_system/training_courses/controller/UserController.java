package com.lms_system.training_courses.controller;

import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // read operation
    @GetMapping()
    public List<User> fetchUserList(HttpSession session) {
        return userService.fetchUserList();
    }

    // update operation
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        return userService.updateUser(user, userId);
    }

    // delete operation
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
        return "Deleted Successfully";
    }
}
