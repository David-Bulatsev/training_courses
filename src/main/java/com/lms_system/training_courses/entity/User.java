package com.lms_system.training_courses.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @NotBlank(message = "User name have to be filled")
    @Size(message = "The name length should not exceed 32 characters")
    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "VARCHAR", nullable = false)
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

    @Column(name = "admin_rules", nullable = false)
    private boolean adminRules = false;

    // сет курсов, на которые записан пользователь
    // сет - у курса не может быть одного и того же юзера дважды
//    @JsonBackReference // "Подчиненная" сторона (игнорируется)
    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;


}
