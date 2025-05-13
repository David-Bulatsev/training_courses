package com.lms_system.training_courses.controller;


import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registration(@RequestBody User user) {
        try {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/lms/login")
    public ResponseEntity<?> login() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
