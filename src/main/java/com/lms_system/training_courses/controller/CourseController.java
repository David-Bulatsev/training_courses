package com.lms_system.training_courses.controller;

import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.exception.NotFoundException;
import com.lms_system.training_courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/{courseId}/assign")
    public Course assignUser(@PathVariable("courseId") Long courseId,
                             @RequestParam("userId") Long userId) throws NotFoundException {
        return courseService.addUserIntoCourse(userId, courseId);
    }
}
