package com.lms_system.training_courses.service.impl;

import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.exception.NotFoundException;
import com.lms_system.training_courses.repository.CourseRepository;
import com.lms_system.training_courses.repository.UserRepository;
import com.lms_system.training_courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements CourseService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course addUserIntoCourse(Long userId, Long courseId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Курс с id " + courseId + " не найден"));
        user.getCourses().add(course);
        course.getUsers().add(user);
        return courseRepository.save(course);
    }
}

