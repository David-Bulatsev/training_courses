package com.lms_system.training_courses.dto.mapper;

import com.lms_system.training_courses.dto.CourseDTO;
import com.lms_system.training_courses.entity.Course;
import com.lms_system.training_courses.entity.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CourseMapper {
    public CourseDTO toDto(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setUserIds(
                course.getUsers().stream()
                        .map(User::getId)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}