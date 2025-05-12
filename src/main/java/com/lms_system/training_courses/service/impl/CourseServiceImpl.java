package com.lms_system.training_courses.service.impl;

import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.exception.NotFoundException;
import com.lms_system.training_courses.repository.CourseRepository;
import com.lms_system.training_courses.repository.UserRepository;
import com.lms_system.training_courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Primary
public class CourseServiceImpl implements CourseService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course addUserIntoCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Курс с id " + courseId + " не найден"));
        user.getCourses().add(course);
        course.getUsers().add(user);
        return courseRepository.save(course);
    }

    @Override
    public User deleteUserFromCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Курс с id " + courseId + " не найден"));
        user.getCourses().remove(course);
        course.getUsers().remove(user);
        return userRepository.save(user);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> filterByCategory(String category) {
        return courseRepository.findAllByCategory(category);
    }

    @Override
    public List<Course> filterByAuthor(String authorName) {
        return courseRepository.findAllByAuthor_Name(authorName);
    }

    @Override
    public List<Course> filterByDuration(int duration) {
        return courseRepository.findAllByDuration(duration);
    }

    @Override
    public List<Course> sortByCategory() {
        return List.of();
    }

    @Override
    public List<Course> sortByDate() {
        return List.of();
    }

    @Override
    public List<Course> sortByDuration() {
        return List.of();
    }
}

