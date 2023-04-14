package com.demo.lms.CourseService.query.rest;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CourseRestModel {
	private  String courseId;
    private  String title;
    private  BigDecimal hours;
    private  String technology;
}
