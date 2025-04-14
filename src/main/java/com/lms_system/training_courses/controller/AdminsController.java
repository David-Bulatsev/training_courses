package com.lms_system.training_courses.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("lms/admins/")
public class AdminsController {

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String mainPageForAdmins() { return "This is page for admins"; }

}
