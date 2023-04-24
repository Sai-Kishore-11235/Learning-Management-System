package com.demo.lms.CourseService.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.lms.CourseService.core.data.AggregateRepository;
import com.demo.lms.CourseService.core.data.CourseMongoEntity;
import com.demo.lms.CourseService.core.data.CourseRepository;
import com.demo.lms.CourseService.query.rest.CourseRestModel;

@Component
public class CoursesQueryHandler {
	public final AggregateRepository aggregateRepository;
	public final CourseRepository courseRepository;
	
	public CoursesQueryHandler(AggregateRepository aggregateRepository,CourseRepository courseRepository) {
		this.aggregateRepository = aggregateRepository;
		this.courseRepository = courseRepository;
	}
	
	@QueryHandler
	 public  List<CourseRestModel> findCourses(FindAllCoursesQuery query) {
		List<CourseRestModel> courseRest = new ArrayList<>();
		List<CourseMongoEntity> storedCourses = aggregateRepository.findAll();
		for(CourseMongoEntity courseEntity : storedCourses) {
			CourseRestModel courseRestModel = new CourseRestModel();
			BeanUtils.copyProperties(courseEntity, courseRestModel);
			courseRest.add(courseRestModel);
		}
		return courseRest;
		
	}
	@QueryHandler
	 public  List<CourseRestModel> findCoursesByTechnology(String technology) {
		List<CourseRestModel> courseRest = new ArrayList<>();
		List<CourseMongoEntity> storedCourses = aggregateRepository.findByTechnology(technology);
		for(CourseMongoEntity courseEntity : storedCourses) {
			CourseRestModel courseRestModel = new CourseRestModel();
			BeanUtils.copyProperties(courseEntity, courseRestModel);
			courseRest.add(courseRestModel);
		}
		return courseRest;
		
	}
	
	@QueryHandler
	 public  List<CourseRestModel> findCoursesByTechnologyAndDuration(FindCoursesQuery findCoursesQuery ) {
		List<CourseRestModel> courseRest = new ArrayList<>();
		List<CourseMongoEntity> storedCourses = aggregateRepository.findByTechnologyAndHoursBetween(findCoursesQuery.getTechnology(),findCoursesQuery.getDurationFromRange(),findCoursesQuery.getDurationToRange());
		for(CourseMongoEntity courseEntity : storedCourses) {
			CourseRestModel courseRestModel = new CourseRestModel();
			BeanUtils.copyProperties(courseEntity, courseRestModel);
			courseRest.add(courseRestModel);
		}
		return courseRest;
		
	}
}
