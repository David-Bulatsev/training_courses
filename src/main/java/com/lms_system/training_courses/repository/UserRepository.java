package com.lms_system.training_courses.repository;

import com.lms_system.training_courses.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
