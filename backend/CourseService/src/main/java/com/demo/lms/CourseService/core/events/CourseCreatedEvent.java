package com.demo.lms.CourseService.core.events;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CourseCreatedEvent {
	private  String courseId;
    private  String title;
    private  BigDecimal hours;
    private  String technology;
}
