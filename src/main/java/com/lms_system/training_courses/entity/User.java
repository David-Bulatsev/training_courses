package com.lms_system.training_courses.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User nickname have to be filled")
    @Size(message = "The nickname length should not exceed 32 characters")
    @Column(nullable = false, length = 32)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 32)
    private String fullname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Role role;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private boolean adminRules = false;

    // сет курсов, на которые записан пользователь
    // сет - у курса не может быть одного и того же юзера дважды
    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;


}
