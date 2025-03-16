package com.lms_system.training_courses.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User nickname have to be filled")
    @Size(message = "The nickname length should not exceed 32 characters")
    @Column(name = "nickname", nullable = false, length = 32)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false, length = 32)
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Column(name = "courses")
    private Integer courses;








}
