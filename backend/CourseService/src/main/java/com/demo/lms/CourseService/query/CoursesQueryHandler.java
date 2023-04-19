package com.demo.lms.CourseService.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.lms.CourseService.core.data.CourseEntity;
import com.demo.lms.CourseService.core.data.CourseRepository;
import com.demo.lms.CourseService.query.rest.CourseRestModel;

@Component
public class CoursesQueryHandler {
	public final CourseRepository courseRepository;
	public CoursesQueryHandler(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@QueryHandler
	 public  List<CourseRestModel> findCourses(FindCoursesQuery query) {
		List<CourseRestModel> courseRest = new ArrayList<>();
		List<CourseEntity> storedCourses = courseRepository.findAll();
		for(CourseEntity courseEntity : storedCourses) {
			CourseRestModel courseRestModel = new CourseRestModel();
			BeanUtils.copyProperties(courseEntity, courseRestModel);
			courseRest.add(courseRestModel);
		}
		return courseRest;
		
	}
	@QueryHandler
	 public  List<CourseRestModel> findCoursesByTechnology(String technology) {
		List<CourseRestModel> courseRest = new ArrayList<>();
		List<CourseEntity> storedCourses = courseRepository.findByTechnology(technology);
		for(CourseEntity courseEntity : storedCourses) {
			CourseRestModel courseRestModel = new CourseRestModel();
			BeanUtils.copyProperties(courseEntity, courseRestModel);
			courseRest.add(courseRestModel);
		}
		return courseRest;
		
	}
}
