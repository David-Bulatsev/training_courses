package com.lms_system.training_courses.controller;


import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lms/")
public class Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/all")
    public String pageForAll() { return "This is page for all"; }

}
