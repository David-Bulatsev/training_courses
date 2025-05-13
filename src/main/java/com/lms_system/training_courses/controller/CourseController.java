package com.lms_system.training_courses.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.lms_system.training_courses.dto.CourseDTO;
import com.lms_system.training_courses.dto.mapper.CourseMapper;
import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.exception.NotFoundException;
import com.lms_system.training_courses.service.CourseService;
import com.lms_system.training_courses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/lms/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final UserService userService;

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper, UserService userService) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses().stream().map(courseMapper::toDto).toList(),
                HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<?> saveCourse(@RequestBody Course course,
                             @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userService.getUserByName(userDetails.getUsername());
            course.setAuthor(user);
            course.setUsers(new HashSet<>());
            return new ResponseEntity<>(courseMapper.toDto(courseService.addUserIntoCourse(user.getId(),
                    courseService.saveCourse(course).getId())),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<?> updateCourse(@RequestBody Course course, @PathVariable("id") Long courseId,
                                       @AuthenticationPrincipal UserDetails userDetails){
        try {
            if (courseService.getById(courseId).getAuthor().getName().equals(userDetails.getUsername())) {
                Course updCourse = courseService.updateCourse(course, courseId);
                return new ResponseEntity<>(updCourse, HttpStatus.OK);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long courseId,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        try {
            if (courseService.getById(courseId).getAuthor().getName().equals(userDetails.getUsername())) {
                User user = userService.getUserByName(userDetails.getUsername());
                user.getCourses().remove(courseService.getById(courseId));
                userService.updateUser(user, user.getId());
                courseService.deleteCourse(courseId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/{courseId}/assign")
    public ResponseEntity<?> assignUser(@PathVariable("courseId") Long courseId,
                                @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long userId = userService.getUserByName(userDetails.getUsername()).getId();
            Course course = courseService.addUserIntoCourse(userId, courseId);
            return new ResponseEntity<>(courseMapper.toDto(course), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
