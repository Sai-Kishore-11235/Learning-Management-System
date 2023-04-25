package com.demo.lms.CourseService.query.rest;


import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lms.CourseService.CourseServiceApplication;
import com.demo.lms.CourseService.query.FindAllCoursesQuery;
import com.demo.lms.CourseService.query.FindCoursesQuery;

@RestController
@RequestMapping("/api/v1.0/lms/courses")
public class CourseQueryController {
	
	private final QueryGateway queryGateway;
	public CourseQueryController(QueryGateway queryGateway) {
		this.queryGateway= queryGateway;
	}
	
	@GetMapping("/getall")
	public List<CourseRestModel> getCourses(){
		
		FindAllCoursesQuery findAllCoursesQuery  = new FindAllCoursesQuery();
		List<CourseRestModel> courses = queryGateway.query(findAllCoursesQuery, ResponseTypes.multipleInstancesOf(CourseRestModel.class)).join();
		return courses;
	}
	
	@GetMapping("/info/{technology}")
	public List<CourseRestModel> getCoursesByTechnology(@PathVariable String technology){
		
//		FindCoursesQuery findCoursesQuery  = new FindCoursesQuery();
		List<CourseRestModel> courses = queryGateway.query(technology, ResponseTypes.multipleInstancesOf(CourseRestModel.class)).join();
		return courses;
	}
	
	@GetMapping("/get/{technology}/{durationFromRange}/{durationToRange}")
	public List<CourseRestModel> getCoursesByTechonologyAndDuration(@PathVariable String technology,@PathVariable String durationFromRange,@PathVariable String durationToRange){
//		queryGateway.query(null, null)
		FindCoursesQuery findCoursesQuery  = new FindCoursesQuery();
		findCoursesQuery.setDurationFromRange(durationFromRange);
		findCoursesQuery.setDurationToRange(durationToRange);
		findCoursesQuery.setTechnology(technology);
		List<CourseRestModel> courses = queryGateway.query(findCoursesQuery, ResponseTypes.multipleInstancesOf(CourseRestModel.class)).join();
		return courses;
	}
}

