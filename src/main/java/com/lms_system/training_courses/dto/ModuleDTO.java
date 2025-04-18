package com.lms_system.training_courses.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModuleDTO {
    private Long id;

    private String title;

    private String description;

    private Long courseId;

    public ModuleDTO(Long courseId) {
        this.courseId = courseId;
    }

}