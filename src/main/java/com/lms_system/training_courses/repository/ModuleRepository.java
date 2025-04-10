package com.lms_system.training_courses.repository;

import com.lms_system.training_courses.entity.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {
}
