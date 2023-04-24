package com.demo.lms.CourseService.query;

import lombok.Data;

@Data
public class FindCoursesQuery {

	private String technology;
	
	private String durationFromRange;
	
	private String durationToRange;
}
