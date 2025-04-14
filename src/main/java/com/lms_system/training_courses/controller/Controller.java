package com.lms_system.training_courses.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lms/")
public class Controller {

    @GetMapping("/welcome")
    public String homePage() {
        return "Home Page";
    }

    @GetMapping("/students")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public String mainPageForStudent() { return "This is page for students"; }

    @GetMapping("/teachers")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public String mainPageForTeachers() { return "This is page for teachers"; }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String mainPageForAdmins() { return "This is page for admins"; }

    @GetMapping("/all")
    public String pageForAll() { return "This is page for all"; }

}
