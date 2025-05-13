package com.lms_system.training_courses.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lms/students/")
@PreAuthorize("hasAuthority('ROLE_STUDENT')")
public class StudentsController {

    @GetMapping()
    public String mainPageForStudent() { return "This is page for students"; }

}