package com.demo.lms.CourseService.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, String> {
	
	CourseEntity findByCourseId(String courseId);
	
	CourseEntity findByCourseIdOrTitle(String courseId , String title);

}
