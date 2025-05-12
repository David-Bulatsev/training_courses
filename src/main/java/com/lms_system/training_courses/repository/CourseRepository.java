package com.lms_system.training_courses.repository;

import com.lms_system.training_courses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleLike(String title);

    List<Course> findAllByCategory(String category);

    List<Course> findAllByAuthor_Name(String authorName);

    List<Course> findAllByDuration(int duration);


    // Сортировка по категории (A-Z)
    // @Query("SELECT c FROM Course c ORDER BY c.category ASC")
    List<Course> findAllByOrderByCategoryAsc();

    // Сортировка по автору (сначала новые)
    // @Query("SELECT c FROM Course c ORDER BY c.author DESC")
    List<Course> findAllByOrderByCreatedAtDesc();

    // Сортировка по длительности (самые длинные)
    // @Query("SELECT c FROM Course c ORDER BY c.duration ASC")
    List<Course> findAllByOrderByDurationAsc();

}
