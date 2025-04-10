package com.lms_system.training_courses.entity;


import jakarta.persistence.*;
import org.springframework.boot.Banner;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "theme")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
