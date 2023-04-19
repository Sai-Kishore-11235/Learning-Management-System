package com.demo.lms.CourseService.core.events;

import lombok.Data;

@Data
public class CourseDeletedEvent {

	private String courseId;
    private String title;
}
