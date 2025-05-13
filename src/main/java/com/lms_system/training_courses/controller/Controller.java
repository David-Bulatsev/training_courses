package com.lms_system.training_courses.controller;


import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Controller(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/unauth/welcome")
    public String homePage() {
        return "Home Page";
    }

    @PostMapping("/unauth/registration")
    public User registration(@RequestBody User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userService.saveUser(user);
    }

    @PostMapping("/lms/login")
    public String login() {
        return "Success login";
    }

    @GetMapping("/lms/all")
    public String pageForAll() { return "This is page for all"; }

}
