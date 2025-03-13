package com.lms_system.training_courses;

import com.lms_system.training_courses.controller.Controller;
import com.lms_system.training_courses.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingCoursesApplication.class, args);

	}

	UserController uc = new UserController();
}
