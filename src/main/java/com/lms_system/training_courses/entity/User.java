package com.lms_system.training_courses.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 64)
    private String fullname;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "VARCHAR")
    private Role role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private boolean adminRules = false;

    @OneToMany(mappedBy = "author")
    private List<Course> courses;


}
