package com.lms_system.training_courses.entity;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_TEACHER("ROLE_TEACHER"),
    ROLE_STUDENT("ROLE_STUDENT");

    private final String name;

    Role(String name) {
        this.name = name;
    }

}
