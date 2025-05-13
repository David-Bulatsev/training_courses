package com.lms_system.training_courses.controller;

import com.lms_system.training_courses.dto.CourseDTO;
import com.lms_system.training_courses.dto.mapper.CourseMapper;
import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.exception.NotFoundException;
import com.lms_system.training_courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unauth/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @GetMapping()
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping()
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
//    @PutMapping()
//    @DeleteMapping()

    @PostMapping("/{courseId}/assign")
    public CourseDTO assignUser(@PathVariable("courseId") Long courseId,
                                @RequestParam("userId") Long userId) throws NotFoundException {
        Course course = courseService.addUserIntoCourse(userId, courseId);
        return courseMapper.toDto(course);
    }
}
