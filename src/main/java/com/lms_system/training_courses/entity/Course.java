package com.lms_system.training_courses.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(nullable = false, length = 32)
    private String duration;

    @Column(nullable = false, length = 64)
    private String tag;

    @Column(nullable = false, length = 32)
    private String category;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Module> modules;

    // сет пользователей, имеющих данный курс
    // сет - юзер не может повторно записаться на курс
    @ManyToMany
    private Set<User> users;
}
