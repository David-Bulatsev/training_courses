package com.lms_system.training_courses.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Controller {

    @GetMapping("")
    public String homePage() {
        return "Hello!";
    }
}
