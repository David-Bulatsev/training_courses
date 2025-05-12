package com.lms_system.training_courses.service;

import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface CourseService {

    @Transactional
    Course addUserIntoCourse(Long userId, Long courseId);

    @Transactional
    User deleteUserFromCourse(Long userId, Long courseId);

    @Transactional
    Course saveCourse(Course course);

    List<Course> getAllCourses();


    // Фильтрация по категории, автору курса, длительности в неделях
    List<Course> filterByCategory(String category);

    List<Course> filterByAuthor(String author);

    List<Course> filterByDuration(int duration);

    // Сортировка по категории, дате создания, длительности в неделях
    List<Course> sortByCategory();

    List<Course> sortByDate();

    List<Course> sortByDuration();
}
