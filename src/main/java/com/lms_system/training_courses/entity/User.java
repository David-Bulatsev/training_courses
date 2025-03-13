package com.lms_system.training_courses.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

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
    @Column
    private String nickname;
}
