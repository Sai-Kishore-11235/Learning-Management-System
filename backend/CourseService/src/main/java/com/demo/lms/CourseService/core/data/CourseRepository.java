package com.demo.lms.CourseService.core.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, String> {
	
	CourseEntity findByCourseId(String courseId);
	
	List<CourseEntity> findByTechnology(String technology);
	
	CourseEntity findByCourseIdOrTitle(String courseId , String title);
	
	
	 String deleteByTitle(String title);
}
