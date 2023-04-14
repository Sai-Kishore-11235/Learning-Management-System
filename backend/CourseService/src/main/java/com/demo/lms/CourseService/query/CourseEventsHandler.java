package com.demo.lms.CourseService.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.lms.CourseService.core.data.CourseEntity;
import com.demo.lms.CourseService.core.data.CourseRepository;
import com.demo.lms.CourseService.core.events.CourseCreatedEvent;

@Component
public class CourseEventsHandler {
	private final CourseRepository courseRepository;
	
	public CourseEventsHandler(CourseRepository courseRepository) {
		this.courseRepository=courseRepository;
	}
	@EventHandler
	public void on (CourseCreatedEvent event) {
		CourseEntity courseEntity= new CourseEntity();
		BeanUtils.copyProperties(event, courseEntity);
		courseRepository.save(courseEntity);
		
	}

}
