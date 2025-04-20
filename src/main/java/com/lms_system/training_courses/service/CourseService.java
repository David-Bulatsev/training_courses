package com.lms_system.training_courses.service;

import com.lms_system.training_courses.entity.Course;
import org.springframework.transaction.annotation.Transactional;

public interface CourseService {

    @Transactional
    Course addUserIntoCourse(Long userId, Long courseId);
}
